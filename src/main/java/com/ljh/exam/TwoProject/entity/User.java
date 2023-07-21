package com.ljh.exam.TwoProject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
public class User{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manid;	
	private String id;
    private String pwd;
    private String name;	
    private String nickname;	
    private String phone;

	}
