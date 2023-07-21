package com.ljh.exam.TwoProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.service.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/TwoProject/member/List")
	public List<User> memberList(Model model ){
		
		List<User> memberList = memberService.memberList();
		return memberList;
	}
	
	@GetMapping("/TwoProject/member/Detail")
	public Optional<User> memberDetail(Model model, String id){
		
		Optional<User> user = memberService.memberDetail(id);
		return user;
	}
	
	@GetMapping("/TwoProject/member/Insert")
	public String memberModify(Model model, User user) {
		System.out.println("user :" +user);
	    memberService.memberRegist(user);
	    String message = "Success";
	    return message;
	}
	
	@GetMapping("/TwoProject/member/Delete")
	public String memberDelete(int manid) {
		memberService.memberremove(manid);
		String message ="Success";
		return message;
	}
	
	
}
