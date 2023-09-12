package com.yangtao.config.a_environment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
@Slf4j
public class EnvironmentTest {

    /**
     * Spring 会将加载到的所有配置信息 全部放入Environment中
     */
    @Autowired
    private Environment environment;

    @Test
    void test() {
        String username = environment.getProperty("user.name");
        log.info("user's name is {}", username);
    }
}
