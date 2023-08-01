package com.ljh.exam.TwoProject.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class CheckAuthority {
	
	public String checkLogin(HttpSession httpSession){
        String loginId = (String) httpSession.getAttribute("loginUser");
        if(loginId == null){
            throw new RuntimeException("Wrong access _ Login session information does not exist");
        }
        return loginId;
    }

    public String checkAuthority(HttpSession httpSession){
        String authority = (String) httpSession.getAttribute("authority");
        if(authority == null){
            throw new RuntimeException("Wrong access _ Authority session information does not exist");
        }
        return authority;
    }
		
}
