package com.ljh.exam.TwoProject.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ljh.exam.TwoProject.entity.Notice;
import com.ljh.exam.TwoProject.service.NoticeService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;
	

	
	@GetMapping("/TwoProject/user/notice")
    public String noticeList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Notice> list = null;

        if(searchKeyword == null) {
            list = noticeService.noticeList(pageable);
        }else {
            list = noticeService.noticeSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    
    }
	
	
	
}
