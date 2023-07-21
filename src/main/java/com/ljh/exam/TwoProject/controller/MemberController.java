package com.ljh.exam.TwoProject.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljh.exam.TwoProject.dto.MemberVO;
import com.ljh.exam.TwoProject.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/TwoProject/member/List")
	public String memberList(Model model ){
		
		List<MemberVO> memberList = memberService.memberList();
		model.addAttribute("memberList",memberList);
		return "member/main";
	}
	
}
