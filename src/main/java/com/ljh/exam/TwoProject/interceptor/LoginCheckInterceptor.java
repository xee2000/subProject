package com.ljh.exam.TwoProject.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

	public class LoginCheckInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {

			String requestURI = request.getRequestURI();
			System.out.println("[interceptor] : " + requestURI);
			HttpSession session = request.getSession(false);
			   if (requestURI.startsWith("/resources/") || requestURI.equals("/favicon.ico")) {
			        return true;
			    }
			if(session == null || session.getAttribute("LoginUser") == null) {
	       		// 로그인 되지 않음
				System.out.println("[미인증 사용자 요청]");
				
				//로그인으로 redirect
				response.sendRedirect("/TwoProject/user/LoginForm?redirectURL=" + requestURI);
				return false;
			}
	        // 로그인 되어있을 떄
			return true;
		}

}
