package com.ljh.exam.TwoProject.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
