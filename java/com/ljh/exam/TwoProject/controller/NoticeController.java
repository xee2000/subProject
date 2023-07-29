package com.ljh.exam.TwoProject.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ljh.exam.TwoProject.PersonSpecification.PersonSpecification;
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
                            @PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.DESC)
                            Pageable pageable,
                            String title, String content, String writer) {

        Page<Notice> list = noticeService.noticesearchList(title, content, writer, pageable);
        int nowPage = list.getNumber(); // Add 1 to convert 0-based index to 1-based page number
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        System.out.println("content : " +content);
        System.out.println("list :" +list.getContent());
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
