package com.pyhita.kafka.native_api.g_transaction;

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
public class TransactionProducer {

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
        // 事务是基于幂等性操作的 事务ID
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-tx-id");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(config);
        // 初始化事务
        kafkaProducer.initTransactions();

        try {
            // 开启事务
            kafkaProducer.beginTransaction();
            for (int i = 0; i < 10; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        "test-topic", "key" + i, "value" + i
                );
                kafkaProducer.send(record);
            }
            // 提交事务
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            // 终止事务
            kafkaProducer.abortTransaction();
        }

        kafkaProducer.close();
    }
}
