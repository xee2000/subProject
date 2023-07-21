package com.ljh.exam.TwoProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.mapper.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public List<User> memberList() {
		return memberRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	public Optional<User> memberDetail(String id) {
		// TODO Auto-generated method stub
		return memberRepository.findById(id);
	}


	public void memberRegist(User user) {
		memberRepository.save(user);
		
	}

	public void memberremove(int manid) {
		memberRepository.deleteById(manid);
		
	}

}
