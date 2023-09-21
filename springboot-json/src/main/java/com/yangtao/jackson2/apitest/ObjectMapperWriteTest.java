package com.yangtao.jackson2.apitest;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 14:52
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangtao.jackson2.entity.Car;
import org.junit.Before;
import org.junit.Test;


/**
 * 测试jackson 序列化 java object
 */
public class ObjectMapperWriteTest {

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    // java object --> json
    @Test
    public void test1() throws Exception {
        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

//        objectMapper.writeValue(
//                new FileOutputStream("data/output-2.json"), car);
        String carString = objectMapper.writeValueAsString(car);
        byte[] carByte = objectMapper.writeValueAsBytes(car);

        System.out.println(carString);
        System.out.println(carByte);
    }
}
