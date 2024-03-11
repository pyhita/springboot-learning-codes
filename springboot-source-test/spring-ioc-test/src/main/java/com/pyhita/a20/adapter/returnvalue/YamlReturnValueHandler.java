package com.pyhita.a20.adapter.returnvalue;

import com.pyhita.a20.anno.Yaml;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class YamlReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Yaml yaml = returnType.getMethodAnnotation(Yaml.class);
        return yaml != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        String str = new org.yaml.snakeyaml.Yaml().dump(returnValue);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().print(str);

        // 告诉mvc 请求处理已经完成，不用渲染视图了
        mavContainer.setRequestHandled(true);
    }
}
