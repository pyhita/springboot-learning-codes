package com.pyhita.kafka.native_api.f_sequence;

import com.pyhita.constant.MqConstant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author pyhita
 * @Date 2024/3/31
 * @Description 测试消息有序性
 */
public class SequenceProducer {

    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.ACKS_CONFIG, -1);
        config.put(ProducerConfig.RETRIES_CONFIG, 5);
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 5);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // 开启消息幂等性， 会给每一条消息生成Msg ID：生产者ID+有序ID

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(config);

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test-topic", "key" + i, "value" + i
            );
            kafkaProducer.send(record);
        }

        kafkaProducer.close();
    }
}
