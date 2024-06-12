package com.pyhita.selftest.ioc.lifecycle.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author pyhita
 * @Date 2024/6/12
 * @Description
 */
@Component
public class Dog implements InitializingBean, DisposableBean, BeanNameAware {

    private String name;

    public Dog() {
        System.out.println("Dog # Dog");
    }

    @Autowired
    public void setName(@Value("big dog") String name) {
        System.out.println("6. Dog # setName");
        this.name = name;
    }

    public static void main(String[] args) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("10. Dog # afterPropertiesSet");
    }

    @PostConstruct
    public void myPostConstruct() {
        System.out.println("9. Dog # myPostConstruct");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Dog # destroy");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("7. MyBeanNameAware # setBeanName");
    }
}
