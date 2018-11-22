package com.springmvc.config;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.springmvc.test.TestController;

public class InitWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		List<WebApplicationInitializer> initializers = new LinkedList<>();
		//AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		InitWebAppInitializer webAppInitializer=new InitWebAppInitializer();
		initializers.add(webAppInitializer);
		//ac.register(TestController.class);
        //ac.refresh();
        // Create and register the DispatcherServlet
        InitWebAppInitializer servlet = new InitWebAppInitializer();
        //ServletRegistration.Dynamic registration = sc.addServlet("springmvc", servlet);
        //registration.setLoadOnStartup(1);
//        for (WebApplicationInitializer initializer : initializers) {
//    	    initializer.onStartup(sc);
//    	    sc.addServlet("app", servlet);
//    	}

        //registration.addMapping("/test/*");
	}

}
