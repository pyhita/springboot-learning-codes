package com.pyhita.kafka.native_api.a_quickstart;

import com.pyhita.constant.MqConstant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws Exception {
        // 1 创建ConfigMap 配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.CLUSTER_ZK_BOOTSTRAP_SERVER);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer");

        // 2 创建consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(configMap);

        // 3 订阅topic
        kafkaConsumer.subscribe(List.of("test-topic"));

        // 4 消费消息
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                System.out.println("record = " + record);
            }
        }

        // 5 关闭consumer
//        kafkaConsumer.close();
    }
}
