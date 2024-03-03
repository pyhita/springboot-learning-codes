package com.yangtao.cache.redis.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

//    @Resource
    @Autowired
    private RedisTemplate<String, String> template;

    @GetMapping("/person")

    //    @Cacheable(value = "getPersonById", unless = "#result == null ")
    public String getPersonById(Long personId) {
        if (personId == null) {
            return "empty person";
        }
        ValueOperations<String, String> forValue = template.opsForValue();

        String value = forValue.get("getPersonById");
        if (value != null) {
            return value;
        }

        if (personId == 1) {
            System.out.println("query from db!");
            forValue.set("getPersonById", "person one");
            return "person one";
        }

        // clear cache
        template.expire("getPersonById", 0, TimeUnit.SECONDS);
        return null;
    }

}
