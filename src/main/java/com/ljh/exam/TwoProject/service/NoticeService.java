package com.ljh.exam.TwoProject.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ljh.exam.TwoProject.entity.Notice;
import com.ljh.exam.TwoProject.mapper.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;



	public Optional<Notice> noticeDetail(int manid) {
		// TODO Auto-generated method stub
		return noticeRepository.findById(manid);
	}


	public void noticeRegist(Notice notice) {
		noticeRepository.save(notice);
		
	}

	public void noticeremove(int nno) {
		noticeRepository.deleteById(nno);
		
	}

	public Page<Notice> noticeSearchList(String searchKeyword, Pageable pageable) {
		return noticeRepository.findAll(pageable,searchKeyword);
	}


	public Page<Notice> noticeList(Pageable pageable) {
		
		return noticeRepository.findAll(pageable);
	}

}

