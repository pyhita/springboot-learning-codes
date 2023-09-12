package com.yangtao.config.e_yaml;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class YamlTest {

    @Value("${user.name}")
    private String username;


    @Test
    void test() {
        System.out.println(username);
    }
}
