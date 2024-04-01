package com.pyhita.kafka.native_api.c_poroducer_inteceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author pyhita
 * @Date 2024/4/1
 * @Description 自定义前缀拦截器
 */
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {

    private volatile long success = 0;
    private volatile long failure = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 给message 添加一个统一的前缀:prefix-
        String modifedValue = "prefix-" + record.value();
        return new ProducerRecord<>(
                record.topic(), record.partition(), record.timestamp(),
                record.key(), modifedValue, record.headers()
                );

    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            success++;
        } else {
            failure++;
        }
    }

    @Override
    public void close() {
        double sendSuccessRatio = (double) success / (success + failure);
        System.out.println("消息发送成功率为： " + sendSuccessRatio);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
