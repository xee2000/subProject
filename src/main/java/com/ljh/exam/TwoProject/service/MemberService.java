package com.ljh.exam.TwoProject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljh.exam.TwoProject.dto.MemberVO;
import com.ljh.exam.TwoProject.mapper.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public List<MemberVO> memberList() {
		return memberRepository.memberList();
	}
}
