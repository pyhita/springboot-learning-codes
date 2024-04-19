package com.pyhita.kafka.native_api.h_consumer.consumer_interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * @Author: kante_yang
 * @Date: 2024/4/9
 */

/**
 * 消息设置一个有效期的属性，如果某条消息在既定的时间窗口内无法到达，那么就会被视为无效，它也就不需要再被继续处理了。
 */
public class ConsumerInterceptorTTL implements ConsumerInterceptor<String, String> {
    public static final long EXPIRE_INTERVAL = 10 * 1000;

    // 在消息被消费之前 被调用
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
        long now = System.currentTimeMillis();
        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<String, String>> tpRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : records.records(tp)) {
                if (now - record.timestamp() < EXPIRE_INTERVAL) {
                    tpRecords.add(record);
                }
            }

            if (!tpRecords.isEmpty()) {
                newRecords.put(tp, tpRecords);
            }
        }

        return new ConsumerRecords<>(newRecords);
    }

    // 在消费位移被提交之前 被调用
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        map.forEach((tp, offset) -> System.out.println(tp + " : " + offset.offset()));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
