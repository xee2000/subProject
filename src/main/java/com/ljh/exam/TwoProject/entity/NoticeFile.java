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
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class NoticeFile {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nfo;
	private String filename;
	private String filesize;
	private String uploadpath;
	private String filetype;
	private int nno;
	@CreatedDate
	private Date regDate;
	
	
}
