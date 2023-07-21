package com.ljh.exam.TwoProject.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class NoticeVO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nno;
    private String title;
    private String content;
    private String manid;
}

