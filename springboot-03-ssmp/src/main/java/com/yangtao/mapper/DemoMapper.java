package com.yangtao.mapper;

import org.springframework.stereotype.Repository;

@Repository
public class DemoMapper {


    public String demo() {
        System.out.println("demo mapper");

        return "demo mapper ..";
    }

}
