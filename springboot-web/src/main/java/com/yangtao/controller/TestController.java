package com.yangtao.controller;

import com.yangtao.entity.Person;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2023/12/15
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {

        return "....";
    }

    // 测试springboot 内容协商机制
    @GetMapping("/person")
    public Person person() {

        return Person.builder()
            .name("kante")
            .age(22)
            .birthday(new Date())
            .build();
    }

}
