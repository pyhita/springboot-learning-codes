package com.pyhita.kafka.native_api.e_async;

import com.pyhita.constant.MqConstant;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author pyhita
 * @Date 2024/3/30
 * @Description kafka异步生产者
 */
public class AsyncProducer {

    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(config);

        for (int i = 0; i < 10; i++) {
            // 异步发送
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "key" + i, "value" + i);
            kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // 异步回调
                    if (e != null) {
                        System.out.println("发送成功！");
                    }
                }
            });

        }

        kafkaProducer.close();
    }

}
