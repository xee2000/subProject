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
public class NoticeFile {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nfo;
	private String filename;
	private String filesize;
	private String uploadpath;
	private int nno;
	@CreatedDate
	private Date regDate;
	
	
}
