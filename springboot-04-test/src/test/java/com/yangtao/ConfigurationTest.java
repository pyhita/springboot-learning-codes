package com.yangtao;

import com.yangtao.config.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(Message.class)
public class ConfigurationTest {

    @Autowired
    private String msg;

    @Test
    void test1() {
        System.out.println(msg);
    }
}