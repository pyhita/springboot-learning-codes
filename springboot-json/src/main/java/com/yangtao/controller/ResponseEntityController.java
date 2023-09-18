package com.yangtao.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/entity")
public class ResponseEntityController {

    @RequestMapping("/test1")
    public ResponseEntity<String> test1(Integer age) {
        if (age < 18) {
            return new ResponseEntity<>("未成年呢，出去！", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("今天是个好日子！", HttpStatus.OK);
    }

    @RequestMapping("/test2")
    public ResponseEntity<String> test2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom-header", "kante");

        return new ResponseEntity<>("设置响应头！", headers, HttpStatus.OK);
    }

    @RequestMapping("/test3")
    public ResponseEntity<String> test3(Integer age) {
        if (age <= 0) {
            return ResponseEntity.badRequest().body("age不合法");
        } else if (age < 18) {
            return ResponseEntity.notFound().header("myheader", "kante").build();
        } else if (age < 30) {
            return ResponseEntity.ok("三十而立！");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("继续加油！");
        }


    }

    @RequestMapping("/test4")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> test4(Integer age) {
        if (age <= 0) {
            throw new IllegalArgumentException("参数不合法");
        }

        return ResponseEntity.ok("OK");
    }


    @RequestMapping("/test5")
    public ResponseEntity<String> test5(Integer age) {
        if (age <= 0) {
            throw new IllegalStateException("参数不合法");
        }

        return ResponseEntity.ok("OK");
    }

}
