package com.ljh.exam.TwoProject.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
