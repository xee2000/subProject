package com.ljh.exam.TwoProject.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.ljh.exam.TwoProject.PersonSpecification.PersonSpecification;
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

	public void noticewrite(Notice notice) {
		noticeRepository.save(notice);
		
	}


	public Page<Notice> noticeList(Pageable pageable) {
		return noticeRepository.findAll(pageable);
	}


	public Page<Notice> noticesearchList(String title, String content, String writer, Pageable pageable) {
        Specification<Notice> spec = Specification.where(null);

        if (!StringUtils.isEmpty(title)) {
            spec = spec.and(PersonSpecification.equalTitle(title));
        }
        if (!StringUtils.isEmpty(content)) {
            spec = spec.and(PersonSpecification.equalContent(content));
        }
        if (!StringUtils.isEmpty(writer)) {
            spec = spec.and(PersonSpecification.equalWriter(writer));
        }

        return noticeRepository.findAll(spec, pageable);
    }

	
}
