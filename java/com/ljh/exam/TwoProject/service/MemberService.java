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

	public Optional<User> memberDetail(int manid) {
		// TODO Auto-generated method stub
		return memberRepository.findById(manid);
	}


	public void memberRegist(User user) {
		memberRepository.save(user);
		
	}

	public void memberremove(int manid) {
		memberRepository.deleteById(manid);
		
	}

	public void socialkakaoregist(String id, String nickname) {
		User user = new User(id,nickname);
		 memberRepository.save(user);
		
	}

	public int sociallogin(String id) {
		 long count = memberRepository.countById(id);
			switch((int)count) {
			case 0:
				//아이디 존재없음
				return (int)count;
			case 1:
				//아이디 존재
				return (int)count;
				
		}
			return (int)count;
		}

	public User getById(String id) {
	User LoginUser = memberRepository.findById(id);
		return LoginUser;
	}

}

