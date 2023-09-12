package com.yangtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot03SsmpApplication {

    public static void main(String[] args) {
//        覆盖application yaml文件内容
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Springboot03SsmpApplication.class, args);
    }
}
