package com.ljh.exam.TwoProject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

	public class LoginCheckInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
					//바로 직전에 접속한 urI의 주소를 가져온다.
			String requestURI = request.getRequestURI();
			System.out.println("[interceptor] : " + requestURI);
			HttpSession session = request.getSession(false);
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
