package com.yangtao.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.expression.spel.ast.TypeReference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 11:32
 */
public class JacksonJsonToMap {

    public static void main(String[] args) throws Exception {

        // convert json to map
        byte[] jsonData = Files.readAllBytes(Paths.get("/Users/kante_yang/Codes/java-backend/springboot-learning-codes/springboot-json/src/main/resources/data.txt"));
        Map<String, String> map = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();
        map = objectMapper.readValue(jsonData, HashMap.class);
        System.out.println("map : " + map);

        // anthor way
//        map = objectMapper.readValue(jsonData, new TypeReference<HashMap<String,String>>() {});
    }
}
