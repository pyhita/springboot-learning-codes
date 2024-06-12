package com.yangtao;

import com.yangtao.beans.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class Springboot04TestApplication implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext context;

//    @Autowired
//    @Qualifier("dog1")
//    private Dog dog;

//    @Resource(name = "dog2")
//    private Dog dog;


    @Autowired // Autowired + Primary
    private Dog dog;

    public static void main(String[] args) {
        SpringApplication.run(Springboot04TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Dog bean = context.getBean(Dog.class);
//        System.out.println(bean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
