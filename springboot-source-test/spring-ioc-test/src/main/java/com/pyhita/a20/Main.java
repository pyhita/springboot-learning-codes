package com.pyhita.a20;

import com.pyhita.embed.adapter.MyRequestMappingHandlerAdapter;
import com.pyhita.embed.config.WebConfig;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class Main {

	public static void main(String[] args) throws Exception {
		AnnotationConfigServletWebServerApplicationContext ctx = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

		// RequestMappingHandlerMapping：解析@RequestMapping注解，生成路径和目标方法的映射关系
		RequestMappingHandlerMapping handlerMapping = ctx.getBean(RequestMappingHandlerMapping.class);
		MyRequestMappingHandlerAdapter handlerAdapter = ctx.getBean(MyRequestMappingHandlerAdapter.class);
		// 拿到所有保存的映射关系
		handlerMapping.getHandlerMethods().forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});

		// 拿到请求对应的HandlerMethod
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		System.out.println("chain = " + chain);

		// 拿到adapter 反射执行目标方法
		ModelAndView ret = handlerAdapter.invokeHandlerMethod(request, response, (HandlerMethod) chain.getHandler());

	}
}
