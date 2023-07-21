package com.ljh.exam.TwoProject.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
	
	private String writer;
	private int nno;
	private String title;
	private String content;
	private Date regDate;
	private Date updateDate;
	
}
