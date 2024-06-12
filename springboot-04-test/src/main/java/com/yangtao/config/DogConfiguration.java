package com.yangtao.config;

import com.yangtao.beans.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author pyhita
 * @Date 2024/5/22
 * @Description
 */
@Configuration
public class DogConfiguration {

    @Bean
    @Primary
    public Dog dog1() {
        Dog dog1 = new Dog();
        dog1.setName("dog1");
        return dog1;
    }

    @Bean
    public Dog dog2() {
        Dog dog2 = new Dog();
        dog2.setName("dog2");
        return dog2;
    }
}
