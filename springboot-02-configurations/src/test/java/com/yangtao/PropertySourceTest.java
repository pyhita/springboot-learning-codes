package com.yangtao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertySourceTest {

    @Autowired
    private com.yangtao.config.d_propertysource.PropertySourceTest propertySourceTest;

    @Test
    void test() {
        System.out.println(propertySourceTest.getUrl());
    }
}
