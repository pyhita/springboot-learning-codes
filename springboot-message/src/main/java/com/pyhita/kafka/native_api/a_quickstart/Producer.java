package com.pyhita.kafka.native_api.a_quickstart;

import com.pyhita.constant.MqConstant;
import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class Producer {

    public static void main(String[] args) throws Exception {

        // 1 创建ConfigMap
        Map<String, Object> configMap = new HashMap<>();
        // 配置kafka server 地址
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.CLUSTER_ZK_BOOTSTRAP_SERVER);
        // 配置key value序列化方式
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 2 创建Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(configMap);

        for (int i = 0; i < 50; i++) {
            // 3 创建消息对象
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                    "test-topic", "test-key", "test-value"
            );

            // 4 发送消息
            Future<RecordMetadata> send = kafkaProducer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("record partition = " + recordMetadata.partition());
        }
        // 5 关闭生产者
        kafkaProducer.close();
    }
}
