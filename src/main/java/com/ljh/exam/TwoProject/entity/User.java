package com.ljh.exam.TwoProject.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@ToString
@Entity
@NoArgsConstructor
public class User{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manid;	
	private String id;
    private String pwd;
    private String name;	
    private String nickname;	
    private String phone;

	public User(String id, String nickname) {
		this.id = id;
		this.nickname = nickname;
		}
    

	}
