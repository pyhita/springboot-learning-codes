package com.pyhita.a20.anno;

import java.lang.annotation.*;

// 标识用哪个参数 来获取请求头中的token 信息
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
}
