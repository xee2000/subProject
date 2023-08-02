package com.ljh.exam.TwoProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ljh.exam.TwoProject.interceptor.LogInterceptor;
import com.ljh.exam.TwoProject.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LogInterceptor())
	        .order(1)
	        .addPathPatterns("/**");

	    registry.addInterceptor(new LoginCheckInterceptor())
	        .order(2)
	        .addPathPatterns("/**")
	        .excludePathPatterns("/", "/TwoProject/user/LoginForm", "/TwoProject/user/kakaologin", "/logout", "/css/**", "/resources/**","/img/**","/error");
	}


}
