package com.ljh.exam.TwoProject.interceptor;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor {

	public List loginEssential = Arrays.asList("/user/**");

	public List loginInessential = Arrays.asList("/user/notice/**");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String LoginUser = (String) request.getSession().getAttribute("LoginUser");

		if (LoginUser != null) {
			return true;
		}

		else {
			String destUri = request.getRequestURI();
			String destQuery = request.getQueryString();
			String dest = (destQuery == null) ? destUri : destUri + "?" + destQuery;
			request.getSession().setAttribute("dest", dest);
			
			response.sendRedirect("/TwoProject/user/LoginForm");
			return false;
		}
	}
}
