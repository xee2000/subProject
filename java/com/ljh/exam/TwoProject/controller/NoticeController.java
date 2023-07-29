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
	                            @PageableDefault(page = 0, size = 10, sort = "nno", direction = Sort.Direction.DESC)
	                            Pageable pageable,
	                            String searchKeyword){

	        /*검색기능-3*/
	        Page<Notice> list = null;
	        System.out.println("searchKeyword : " +searchKeyword);
	        /*searchKeyword = 검색하는 단어*/
	        if(searchKeyword == null){
	            list = noticeService.noticeList(pageable); //기존의 리스트보여줌
	        }else{
	            list = noticeService.noticesearchList(searchKeyword, pageable); //검색리스트반환
	        }

	        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
	        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
	        int endPage = Math.min(nowPage + 5, list.getTotalPages());

	        //NoticeService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
	        model.addAttribute("list" , list);
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
