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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.service.MemberService;
import com.ljh.exam.TwoProject.service.OauthService;

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
		return"loginForm";
	}
	
	
	
	@GetMapping("/TwoProject/user/kakao/socialLoginForm")
	public String socailLoginForm() {
		return"/member/";
	}
	
	@GetMapping("/TwoProject/kakaologin")
	public String kakaoCallback(@RequestParam String code, Model model) {
		String access_Token = oauthService.getKakaoAccessToken(code);
		JsonObject jsonobject = createKakaoUser(access_Token);
		JsonElement JsonId = jsonobject.get("id");
		String id = JsonId.getAsString();
		 JsonObject properties = jsonobject.getAsJsonObject("properties");
	        String nickname = properties.get("nickname").getAsString();
	      int result = memberService.socialgetById(id);
		 //저장된 id가 아닌경우 회원가입 필요계정
		switch(result) {
		//아이디 존재
		case 1:
			model.addAttribute("nickname",nickname);
			//아이디 미존재
		case 0:	
			memberService.socialkakaoregist(id);
			model.addAttribute("nickname",nickname);
}
		return "main";

	}

	public JsonObject createKakaoUser(String token) {
	    String reqURL = "https://kapi.kakao.com/v2/user/me";

	    // Retrieve user information using access_token
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", "Bearer " + token); // Create header to be transmitted, transmit access_token

	        // Read the JSON type response message obtained through the request
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder result = new StringBuilder();

	        while ((line = br.readLine()) != null) {
	            result.append(line);
	        }
	        br.close();


	        // Parsing JSON with the Gson library
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
