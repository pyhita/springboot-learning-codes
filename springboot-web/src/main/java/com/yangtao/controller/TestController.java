package com.yangtao.controller;

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

}
