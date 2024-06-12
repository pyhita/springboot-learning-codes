package com.pyhita;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIocTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringIocTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
