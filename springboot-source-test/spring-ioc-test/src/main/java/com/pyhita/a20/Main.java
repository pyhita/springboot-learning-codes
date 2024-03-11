package com.pyhita.a20;


import com.pyhita.a20.adapter.MyRequestMappingHandlerAdapter;
import com.pyhita.a20.config.WebConfig;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.StandardCharsets;

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
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test4");
		request.addHeader("token", "x-role-id-xxxxx");
		MockHttpServletResponse response = new MockHttpServletResponse();
		// 为什么是Chain，因为还有拦截器呢
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		System.out.println("chain = " + chain);

		// 拿到adapter 反射执行目标方法，作用就是通过反射 调用控制器方法
		ModelAndView ret = handlerAdapter.invokeHandlerMethod(request, response, (HandlerMethod) chain.getHandler());
		byte[] array = response.getContentAsByteArray();
		System.out.println("return value = " + new String(array, StandardCharsets.UTF_8));
		// 拿到所有的参数解析器
//		List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
//		for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
//			System.out.println(resolver);
//		}

		// 拿到所有的返回值解析器
//		List<HandlerMethodReturnValueHandler> returnValueHandlers = handlerAdapter.getReturnValueHandlers();
//		for (HandlerMethodReturnValueHandler returnValueHandler : returnValueHandlers) {
//			System.out.println(returnValueHandler);
//		}
	}
}
