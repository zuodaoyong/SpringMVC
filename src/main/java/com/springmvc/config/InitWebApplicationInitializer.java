package com.springmvc.config;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.springmvc.test.TestController;

public class InitWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:spring/spring-mvc.xml");
		//AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		//ac.register(TestController.class);
        //ac.refresh();
        //Create and register the DispatcherServlet
        DispatcherServlet servlet=new DispatcherServlet(appContext);
        ServletRegistration.Dynamic registration = sc.addServlet("springmvc", servlet);
        registration.setLoadOnStartup(1);
        //registration.addMapping("/test/*");
	}

}
