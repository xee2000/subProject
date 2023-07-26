package com.ljh.exam.TwoProject.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Notice {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nno;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	private Date updateDate;
	
}
