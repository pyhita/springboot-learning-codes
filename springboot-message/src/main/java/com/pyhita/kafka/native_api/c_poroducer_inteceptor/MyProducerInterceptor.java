package com.pyhita.kafka.native_api.c_poroducer_inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author pyhita
 * @Date 2024/3/30
 */
// 自定义interceptor
@Slf4j
public class MyProducerInterceptor implements ProducerInterceptor<String, String> {

    // 消息发送时 被回调
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        ProducerRecord<String, String> interceptRecord = new ProducerRecord<>(record.topic(), record.key(), record.value() + "-interceptor");
        log.info("拦截到record, {}", record.key());
        return interceptRecord;
    }

    // 收到Broker ack 时被回调
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        log.info("收到了Broker Ack，{}", recordMetadata.toString());
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
