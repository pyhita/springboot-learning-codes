package com.pyhita.a20.config;

import com.pyhita.embed.adapter.MyRequestMappingHandlerAdapter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan("com.pyhita.embed.controller")
public class WebConfig {

	// 内嵌的web 工厂
	@Bean
	public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	// DispatchServlet
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	// 注册DispatchServlet  到容器
	@Bean
	public DispatcherServletRegistrationBean dispatcherServletRegistrationBean() {
		return new DispatcherServletRegistrationBean(dispatcherServlet(), "/");
	}

	// 定义RequestMappingHandlerMapping
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	// 定义RequestMappingHandlerAdapter
	@Bean
	public MyRequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		return new MyRequestMappingHandlerAdapter();
	}

}
