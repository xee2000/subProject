package com.ljh.exam.TwoProject.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ljh.exam.TwoProject.command.NoticeRegistCommnad;
import com.ljh.exam.TwoProject.entity.Notice;
import com.ljh.exam.TwoProject.entity.NoticeFile;
import com.ljh.exam.TwoProject.entity.User;
import com.ljh.exam.TwoProject.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/TwoProject/user/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

	private final NoticeService noticeService;

	@Value("${noticefile.path}")
	private String uploadDir;

	@GetMapping("/list")
	public String boardList(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@PageableDefault(size = 10, sort = "nno", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(name = "searchType", defaultValue = "") String searchType,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {

		Page<Notice> list = noticeService.noticesearchList(searchType, keyword, pageable.withPage(page - 1));
		int nowPage = list.getNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/notice/list";
	}

	@GetMapping("/writeForm")
	public String writeForm(HttpSession session, Model model) {
		User user = (User) session.getAttribute("LoginUser");
		if (user != null) {

			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("user", user.getId());
		}

		return "/notice/writeForm";

	}

	@PostMapping(value = "/writeregist", produces = "text/plain;charset=utf-8")
	public String write(NoticeRegistCommnad noticeReq, MultipartFile files) {
		List<MultipartFile> multiFiles = noticeReq.getUploadFile();
		String savePath = this.uploadDir;
		try {
		List<NoticeFile>noticeFileList = saveFileToAttaches(multiFiles, savePath);
		noticeService.noticewrite(noticeFileList, noticeReq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "redirect:/TwoProject/user/notice";
		return url;
	}
	
	public class FileSizeConverter {
		public static String convertFileSize(long bytes) {
			if (bytes < 1024) {
				return bytes + " B";
			} else if (bytes < 1024 * 1024) {
				double kb = bytes / 1024.0;
				return formatDouble(kb) + " KB";
			} else {
				double mb = bytes / (1024.0 * 1024.0);
				return formatDouble(mb) + " MB";
			}
		}

		private static String formatDouble(double value) {
			DecimalFormat df = new DecimalFormat("#.##");
			return df.format(value);
		}
	}
	
	private List<NoticeFile> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception {
		List<NoticeFile> noticeFileList = new ArrayList<NoticeFile>();
		// 저장 -> attachVO -> list.add
		if (multiFiles != null) {
			for (MultipartFile multi : multiFiles) {
				String fileName = com.ljh.exam.TwoProject.util.MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				target.mkdirs();
				multi.transferTo(target);

				NoticeFile noticefile = new NoticeFile();
				noticefile.setUploadpath(savePath);
				noticefile.setFilename(fileName);
				noticefile.setFiletype(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
				long fileSizeInBytes = multi.getSize();
				String formattedFileSize = FileSizeConverter.convertFileSize(fileSizeInBytes);
				noticefile.setFilesize(formattedFileSize);
				noticeFileList.add(noticefile);
			}
		}
		return noticeFileList;
	}
}
