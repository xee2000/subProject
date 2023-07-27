package com.ljh.exam.TwoProject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			session.setMaxInactiveInterval(600*6);
			model.addAttribute("LoginUser",LoginUser.getNickname());
			break;
			//아이디 미존재
		case 0:	
			memberService.socialkakaoregist(id,nickname);
			break;
}
		 return "redirect:/TwoProject/user/notice";

	}

	public JsonObject createKakaoUser(String token) {
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    
	    try {
	        // These two structures bring the URL that comes with it. If the URL does not have a problem such as security
	        // Used to process data on the web, etc. of the address
	        URL url = new URL(reqURL);
	        // This method defaults to GET
	        // url.openConnection is implemented through httpUrlconnection
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        // If the above specifies the method, the below specifies in the form of key and value
	        conn.setRequestProperty("Authorization", "Bearer " + token);
	        
	        // Bufferereader is a concept similar to Scanner. Here, the declaration proceeds.
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder result = new StringBuilder();
	            while ((line = br.readLine()) != null) {
	                result.append(line);
	            }
	            
	            Gson gson = new Gson();
	            JsonObject jsonObject = gson.fromJson(result.toString(), JsonObject.class);
	            return jsonObject;
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
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
