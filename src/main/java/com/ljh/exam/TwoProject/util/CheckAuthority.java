package com.ljh.exam.TwoProject.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class CheckAuthority {
	
	public String checkLogin(HttpSession httpSession){
        String LoginUser = (String) httpSession.getAttribute("LoginUser");
        if(LoginUser == null){
            throw new RuntimeException("Wrong access _ Login session information does not exist");
        }
        return LoginUser;
    }

    public String checkAuthority(HttpSession httpSession){
        String authority = (String) httpSession.getAttribute("authority");
        if(authority == null){
            throw new RuntimeException("Wrong access _ Authority session information does not exist");
        }
        return authority;
    }
		
}
