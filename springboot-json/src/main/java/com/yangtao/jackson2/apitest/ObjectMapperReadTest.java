package com.yangtao.jackson2.apitest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangtao.jackson2.entity.Car;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 14:41
 */

/**
 * objectmapper 反序列化测试
 */
public class ObjectMapperReadTest {


    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    // json string -> java object
    @Test
    public void test1() throws Exception {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }

    // json字符输入流 -> Java对象
    @Test
    public void test2() throws Exception {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        Reader reader = new StringReader(carJson);

        Car car = objectMapper.readValue(reader, Car.class);
        System.out.println(car);
    }

    // json file --> java object
    @Test
    public void test3() throws Exception {
        File file = new File("data/car.json");

        Car car = objectMapper.readValue(file, Car.class);
    }


    // json via url --> java object
    @Test
    public void test4() throws Exception {
        URL url = new URL("file:data/car.json");

        Car car = objectMapper.readValue(url, Car.class);
    }

    // json 字节输入流 --> java object
    @Test
    public void test5() throws Exception {
        InputStream input = new FileInputStream("data/car.json");

        Car car = objectMapper.readValue(input, Car.class);
    }







}
