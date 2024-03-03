package com.yangtao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/test")
    public String test(String a, String b) {
        // 记录日志
        log.trace("trace 日志");
        log.debug("debug日志");
        log.info("info 日志 a: {}, b: {}", a, b);
        log.warn("warn 日志");
        log.error("error 日志");

        return "test ...";
    }
}
