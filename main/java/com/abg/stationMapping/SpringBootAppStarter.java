package com.abg.stationMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.abg.stationMapping.config.AppProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootAppStarter {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootAppStarter.class, args);

		DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

}