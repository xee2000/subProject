package com.ljh.exam.TwoProject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.service.MemberService;
import com.ljh.exam.TwoProject.service.OauthService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final OauthService oauthService;
	
	
	@RequestMapping("/TwoProject/user/main")
	public String usermain() {
		return "main";
	}
	
	@GetMapping("/TwoProject/user/LoginForm")
	public String loginForm() {
		return"/user/loginForm";
	}
	
	
	
	@GetMapping("/TwoProject/user/kakao/socialLoginForm")
	public String socailLoginForm() {
		return"/member/";
	}
	
	@GetMapping("/TwoProject/kakaologin")
	public String kakaoCallback(@RequestParam String code, Model model, HttpSession session) {
		//주소줄에 들어온 code값을 통해 access_token즉 사용자 인증정보가 담긴 토큰으로 리턴
		String access_Token = oauthService.getKakaoAccessToken(code);
		//받아온 토큰을 json형태로 바꿔서 실제 데이터처리가 가능하도록 형태변환
		JsonObject jsonobject = createKakaoUser(access_Token);
		//사용자 고유 id값을 가져온다 이 id값은 유니크하다
		JsonElement JsonId = jsonobject.get("id");
		String id = JsonId.getAsString();
		//properties에는 사용자가 동의한 내용에 따른 고유 정보가 담겨있다.
		 JsonObject properties = jsonobject.getAsJsonObject("properties");
	        String nickname = properties.get("nickname").getAsString();
	      int result = memberService.sociallogin(id);
		 //저장된 id가 아닌경우 회원가입 필요계정
		switch(result) {
		//아이디 존재
		case 1:
			User LoginUser = memberService.getById(id);
			System.out.println("LoginUser :" +LoginUser.getNickname());
			session.setAttribute("LoginUser", LoginUser);
			session.setMaxInactiveInterval(5);
			model.addAttribute("LoginUser",LoginUser.getNickname());
			break;
			//아이디 미존재
		case 0:	
			memberService.socialkakaoregist(id,nickname);
			break;
}
		return "main";

	}
	
	@CrossOrigin("*")
	public class LoginController {
	    @Value("${google.client.id}")
	    private String googleClientId;
	    @Value("${google.client.pw}")
	    private String googleClientPw;

	    @RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.POST)
	    public String loginUrlGoogle(){
	        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
	                + "&redirect_uri=http://localhost:8080/api/v1/oauth2/google&response_type=code&scope=email%20profile%20openid&access_type=offline";
	        return reqUrl;
	    }

	}

	public JsonObject createKakaoUser(String token) {
	    String reqURL = "https://kapi.kakao.com/v2/user/me";

	    try {
	    	//해당 두개의 구조는 같이 딸려온다 url을 가져오게 되는데 그 url이 보안등의 문제가 없을경우
	    	//해당 주소지의 웹등에서 데이터를 처리하는데 사용
	        URL url = new URL(reqURL);
	        //해당 메서드는 기본적으로 GET이 기본방식이다
	        //url.openConnection은 httpUrlconnection을 통해 구현된것 
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("GET");
	        //위가 메서드를 규정하는거면 아래에서는 key와 value형태로 지정한다
	        conn.setRequestProperty("Authorization", "Bearer " + token); 
	        
	        //bufferereader는 scanner와 유사한 개념 여기선 선언이 진행된다.
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder result = new StringBuilder();

	        while ((line = br.readLine()) != null) {
	            result.append(line);
	        }
	        br.close();


	        Gson gson = new Gson();
	        JsonObject jsonObject = gson.fromJson(result.toString(), JsonObject.class);

	       return jsonObject;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
		@GetMapping("/TwoProject/member/List")
		public String getMemberListAsJson() {
		    List<User> memberList = memberService.memberList();
		    Gson gson = new Gson();
		    String json = gson.toJson(memberList);
		    return json;
		}
	
	@GetMapping("/TwoProject/member/Detail")
	public Optional<User> memberDetail(Model model, int manid){
		
		Optional<User> user = memberService.memberDetail(manid);
		return user;
	}
	
	@GetMapping("/TwoProject/member/Insert")
	public Optional<User> memberModify(Model model, User user) {
		System.out.println("user :" +user);
	    memberService.memberRegist(user);
	    Optional<User> userdetail = memberService.memberDetail(user.getManid());
	    return userdetail;	
	}
	
	@GetMapping("/TwoProject/member/Delete")
	public String memberDelete(int manid) {
		memberService.memberremove(manid);
		String message ="Success";
		return message;
	}
	
	
}
