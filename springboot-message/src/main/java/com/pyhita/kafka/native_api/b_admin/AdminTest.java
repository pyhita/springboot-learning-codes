package com.pyhita.kafka.native_api.b_admin;


import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminTest {

    // 通过admin 创建topic
    public static void main(String[] args) {
        HashMap<String, Object> configMap = new HashMap<>();
        Admin admin = Admin.create(configMap);

        // 利用admin 来创建topic
        // 1 采用kafka默认的分区算法 创建topic1， 有两个分区，两个副本
        // 2 采用自定义的分区算法 创建topic2
        int partition = 2;
        short replicas = 2;
        List<NewTopic> topics = List.of(
                new NewTopic("topic1", partition, replicas), // 默认的分区算法
                new NewTopic("topic2", Map.of(
                        0, List.of(1, 2), // partition 0 的两个副本，分别放在BrokerId 1 、BrokerId2
                        1, List.of(2, 3)      // partition 1 的两个副本，分别放在BrokerId 2 、BrokerId3
                ))
        );
        admin.createTopics(topics);
    }
}
