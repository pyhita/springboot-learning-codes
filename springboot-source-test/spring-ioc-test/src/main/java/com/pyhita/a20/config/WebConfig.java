package com.pyhita.a20.config;

import com.pyhita.a20.adapter.MyRequestMappingHandlerAdapter;
import com.pyhita.a20.adapter.returnvalue.YamlReturnValueHandler;
import com.pyhita.a20.resolver.TokenArguResolver;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
@ComponentScan("com.pyhita.a20.controller")
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
		MyRequestMappingHandlerAdapter handlerAdapter = new MyRequestMappingHandlerAdapter();
		handlerAdapter.setArgumentResolvers(List.of(new TokenArguResolver()));
		handlerAdapter.setReturnValueHandlers(List.of(new YamlReturnValueHandler()));
		return handlerAdapter;
	}

}
