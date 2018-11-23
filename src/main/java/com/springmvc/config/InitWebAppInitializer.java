package com.springmvc.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.springmvc.filters.UrlFilter;

public class InitWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/test123/*"};
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new UrlFilter()};
	}
	
	@Override
	protected boolean isAsyncSupported() {
		return super.isAsyncSupported();
	}
	
}
