package com.ljh.exam.TwoProject.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String noticeList(Model model, HttpSession session,@PageableDefault(sort = "nno", direction = Sort.Direction.DESC) Pageable pageable) {
	    List<Notice> noticeList = noticeService.noticeList();

	    Page<Notice> list = noticeService.pageList(pageable);

	    model.addAttribute("posts", list);
	    model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
	    model.addAttribute("next", pageable.next().getPageNumber());
	    model.addAttribute("hasNext", list.hasNext());
	    model.addAttribute("hasPrev", list.hasPrevious());
	    model.addAttribute("noticeList", noticeList);

	    return "notice/list";
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

	@PostMapping("/TwoProject/user/notice/regist")
	public String write(Notice notice) {
		noticeService.noticewrite(notice);
		String url = "redirect:/TwoProject/user/notice";
		return url;
	}
}
