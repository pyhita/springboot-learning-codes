package com.yangtao.config.b_value;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ValueTest {

    // 通过@Value注解 读取配置文件
    @Value("${user.name}")
    private String username;

    @Test
    void test() {
        log.info("user name is {}", username);
    }
}
