package com.ljh.exam.TwoProject.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ljh.exam.TwoProject.entity.Notice;
import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {

	private final NoticeService noticeService;
	@GetMapping("/TwoProject/user/notice")
	public String boardList(Model model,
	                        @RequestParam(name = "page", defaultValue = "1") int page,
	                        @PageableDefault(size = 10, sort = "nno", direction = Sort.Direction.DESC)
	                        Pageable pageable,
	                        @RequestParam(name = "searchType", defaultValue = "") String searchType,
	                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {

	    Page<Notice> list = noticeService.noticesearchList(searchType,keyword, pageable.withPage(page - 1));
	    int nowPage = list.getNumber() + 1; 
	    int startPage = Math.max(nowPage - 4, 1);
	    int endPage = Math.min(nowPage + 5, list.getTotalPages());
	    model.addAttribute("list", list);
	    model.addAttribute("nowPage", nowPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    return "/notice/list";
	}






	@GetMapping("/TwoProject/user/notice/writeForm")
	public String writeForm(HttpSession session, Model model) {
		 User user = (User) session.getAttribute("LoginUser");
		    if (user != null) {
		    	
		    	model.addAttribute("nickname", user.getNickname());
		        model.addAttribute("user", user.getId());
		    }

		return "/notice/writeForm";

	}
	
	
	@PostMapping(value = "/TwoProject/user/notice/writeregist", produces = "text/plain;charset=utf-8")
	public String write(String writer, String title, String content) {
		Notice notice = new Notice(writer,title,content);
		noticeService.noticewrite(notice);
		String url = "redirect:/TwoProject/user/notice";
		return url;
	}
}
