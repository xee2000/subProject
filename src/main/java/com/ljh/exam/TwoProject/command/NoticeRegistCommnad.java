package com.ljh.exam.TwoProject.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class NoticeRegistCommnad {
	
	private List<MultipartFile>uploadFile;
	private String title;
	private String content;
	private String writer;
}
