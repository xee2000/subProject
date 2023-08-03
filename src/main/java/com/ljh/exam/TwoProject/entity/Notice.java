package com.ljh.exam.TwoProject.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Notice {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nno;
	private String writer;
	private String title;
	private String content;
	@CreatedDate
	private Date regDate;
	private Date updateDate;
	
	public Notice(String writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
}
	