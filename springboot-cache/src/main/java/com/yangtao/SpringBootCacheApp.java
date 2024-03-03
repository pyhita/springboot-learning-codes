package com.yangtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

@SpringBootApplication
public class SpringBootCacheApp {
    public static void main(String[] args) {

        SpringApplication.run(SpringBootCacheApp.class, args);
    }
}