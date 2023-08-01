package com.ljh.exam.TwoProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.ljh.exam.TwoProject.PersonSpecification.PersonSpecification;
import com.ljh.exam.TwoProject.command.NoticeRegistCommnad;
import com.ljh.exam.TwoProject.entity.Notice;
import com.ljh.exam.TwoProject.entity.NoticeFile;
import com.ljh.exam.TwoProject.mapper.NoticeRepository;
import com.ljh.exam.TwoProject.mapper.NoticefileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final NoticefileRepository noticefileRepository;

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

	public void noticewrite(Notice notice) {
		
		noticeRepository.save(notice);
		
	}

	public Page<Notice> noticesearchList(String searchType, String keyword, Pageable pageable) {
	    Specification<Notice> spec = Specification.where(null);
	    //내가입력한 값 즉 keyword가 비어잇지 않으면
	    if (!StringUtils.isEmpty(keyword)) {
	    	//검색하려는 serarchType이 title ? conteont? writer이랑 일치하는지 비교하여 마
	        if ("title".equals(searchType)) {
	            spec = spec.and(PersonSpecification.equalTitle(keyword));
	        } else if ("content".equals(searchType)) {
	            spec = spec.and(PersonSpecification.equalContent(keyword));
	        } else if ("writer".equals(searchType)) {
	            spec = spec.and(PersonSpecification.equalWriter(keyword));
	        }
	    }

	    return noticeRepository.findAll(spec, pageable);
	}

	public void noticewrite(List<NoticeFile> noticeFileList, NoticeRegistCommnad noticeReq) {
		String content = noticeReq.getContent();
		String writer = noticeReq.getWriter();
		String title = noticeReq.getTitle();
		Notice notice = new Notice(writer,title,content);
		noticeRepository.save(notice);
	int nno = notice.getNno();
	if(noticeFileList != null) {
		  for (int i = 0; i < noticeFileList.size(); i++) {
			  NoticeFile noticefile = noticeFileList.get(i);
	            noticefile.setNno(nno);
	            noticefileRepository.save(noticefile);
	        }
	}

	}
}
