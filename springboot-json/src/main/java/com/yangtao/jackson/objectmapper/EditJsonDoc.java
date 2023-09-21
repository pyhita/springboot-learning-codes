package com.yangtao.jackson.objectmapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 14:04
 */
public class EditJsonDoc {

    public static void main(String[] args) throws Exception {

        byte[] jsonData = Files.readAllBytes(Paths.get("/Users/kante_yang/Codes/java-backend/springboot-learning-codes/springboot-json/src/main/resources/employee.txt"));
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonData);

        // update json data
        ((ObjectNode) rootNode).put("id", 5000);
        // add  new key
        ((ObjectNode) rootNode).put("test", "test value");
        // rmeote existing key
        ((ObjectNode) rootNode).remove("role");
        objectMapper.writeValue(new File("classpath:updated_emp.txt"), rootNode);
    }
}
