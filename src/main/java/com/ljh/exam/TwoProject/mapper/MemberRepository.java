package com.ljh.exam.TwoProject.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ljh.exam.TwoProject.dto.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

	List<MemberVO> memberList();
	
	
}

