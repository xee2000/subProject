package com.ljh.exam.TwoProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	public List<Notice> noticeList() {
		return noticeRepository.findAll();
	}

	public void noticewrite(Notice notice) {
		noticeRepository.save(notice);
		
	}


	public Page<Notice> pageList(Pageable pageable) {
		
		return noticeRepository.findAll(pageable);
	}
	 

}
