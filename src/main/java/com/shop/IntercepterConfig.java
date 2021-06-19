package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shop.interceptors.Authentcate;
import com.shop.interceptors.RoleInterceptor;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
	@Autowired
	private Authentcate authenInterceptor;
	@Autowired
	private RoleInterceptor roleInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenInterceptor).addPathPatterns("/admin/**", "/user/**")
				.excludePathPatterns("/login", "/register");

		registry.addInterceptor(roleInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/user/","/home");
	}
}
