package com.springmvc.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.springmvc.filters.UrlFilter;

public class InitWebAppInitializer2 extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/test1235/*"};
	}

	@Override
	protected String getServletName() {
		return "asd123";
	}
	
	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		String servletName = getServletName();

		WebApplicationContext servletAppContext = createServletApplicationContext();

		FrameworkServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
		//dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());

		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
		if (registration == null) {
			throw new IllegalStateException("Failed to register servlet with name '" + servletName + "'. " +
					"Check if there is another servlet registered under the same name.");
		}

		registration.setLoadOnStartup(2);
		registration.addMapping(getServletMappings());
		registration.setAsyncSupported(isAsyncSupported());

		Filter[] filters = getServletFilters();
		if (!ObjectUtils.isEmpty(filters)) {
			for (Filter filter : filters) {
				registerServletFilter(servletContext, filter);
			}
		}

		customizeRegistration(registration);
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
