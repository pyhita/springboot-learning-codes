package com.yangtao.jackson2.tree;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 16:05
 */
public class TraverseJsonNode {

    public static void main(String[] args) throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get("/Users/kante_yang/Codes/java-backend/springboot-learning-codes/springboot-json/src/main/resources/employee.txt"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonData);

        // 遍历json node ，打印出所有的 json key name
//        parseJson(root);
        root.fields().forEachRemaining(entry -> {
            System.out.println(entry.getKey());
        });
    }

    private static void parseJson(JsonNode node) {
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            objectNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                System.out.println(key);
                JsonNode jsonNode = entry.getValue();
                parseJson(jsonNode);
            });
        }
    }
}
