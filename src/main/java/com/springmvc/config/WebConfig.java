package com.springmvc.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springmvc.interceptors.TestInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan("com")
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
	}
}
