package com.yangtao.jackson.objectmapper;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 13:59
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Sometimes we have json data and we are interested in only few of the keys values, so in that case converting whole JSON to object is not a good idea.
 * Jackson JSON API provides option to read json data as tree like DOM Parser and we can read specific elements of JSON object through this.
 */
public class JacksonReadSpecificJsonKeys {

    public static void main(String[] args) throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get("/Users/kante_yang/Codes/java-backend/springboot-learning-codes/springboot-json/src/main/resources/employee.txt"));
        ObjectMapper objectMapper = new ObjectMapper();

        // read josn like DOM Parser
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode idNode = rootNode.path("id");
        System.out.println("id = " + idNode.asInt());

        JsonNode phoneNode = rootNode.path("phoneNumbers");
        Iterator<JsonNode> elements = phoneNode.elements();
        while (elements.hasNext()) {
            JsonNode phone = elements.next();
            System.out.println("phone no = " + phone.asLong());
        }

        String id = rootNode.get("id").asText();
        System.out.println("=================");
        System.out.println("id = " + id);
        JsonNode numbers = rootNode.get("phoneNumbers");
        numbers.forEach(json -> {
            System.out.println("phone no = " + json.asText());
        });

        // numbers 是array 可以直接通过index 获取
        System.out.println(numbers.get(0));
        System.out.println(numbers.get(1));
    }
}
