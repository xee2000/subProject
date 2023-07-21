package com.ljh.exam.TwoProject.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ljh.exam.TwoProject.dto.NoticeVO;

public interface NoticeRepository extends JpaRepository<NoticeVO, Integer> {
	
	
}
