package com.pyhita.kafka.native_api.e_async;

import com.pyhita.constant.MqConstant;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author pyhita
 * @Date 2024/3/30
 * @Description kafka同步生产者
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(config);

        for (int i = 0; i < 10; i++) {
            // 同步发送
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "key" + i, "value" + i);
            Future<RecordMetadata> send = kafkaProducer.send(record);
            send.get(); // 阻塞等待
        }

        kafkaProducer.close();
    }

}
