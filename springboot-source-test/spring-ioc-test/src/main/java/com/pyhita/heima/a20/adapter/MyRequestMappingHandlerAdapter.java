package com.pyhita.heima.a20.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

	@Override
	public ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		return super.invokeHandlerMethod(request, response, handlerMethod);
	}
}
