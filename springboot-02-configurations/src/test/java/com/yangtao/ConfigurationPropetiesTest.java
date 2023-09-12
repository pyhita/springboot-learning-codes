package com.yangtao;

import com.yangtao.config.c_configurationproperties.Cat;
import com.yangtao.config.c_configurationproperties.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigurationPropetiesTest {

    @Autowired
    private Dog dog;

    @Autowired
    private Cat cat;

    @Test
    void test() {
        System.out.println(dog.getName());
        System.out.println(dog.getAge());
        System.out.println(cat.getName());
        System.out.println(cat.getKind());
    }
}
