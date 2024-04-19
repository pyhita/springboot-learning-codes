package com.pyhita.kafka.native_api.a_quickstart;

import com.pyhita.constant.MqConstant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @Author: kante_yang
 * @Date: 2024/4/9
 * @Description Kafka 消费者端Demo
 */
public class Consumer2 {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "test-topic";
    public static final String groupId = "group.demo";
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MqConstant.CLUSTER_ZK_BOOTSTRAP_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅 test-topic主题下面的所有分区
        // 首先拿到Topic 下面所有分区的元数据信息
        List<TopicPartition> partitions = new ArrayList<>();
        for (PartitionInfo partitionInfo : consumer.partitionsFor(topic)) {
            partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
        }

        consumer.assign(partitions);

        try {
            while (isRunning.get()) {
                // 拉取到一批消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                // for (ConsumerRecord<String, String> record : records) {
                //     System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset());
                // }
                for (TopicPartition partition : records.partitions()) {
                    // 按照分区 对消息进行消费
                    for (ConsumerRecord<String, String> record : records.records(partition)) {
                        System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset());
                    }
                }
            }
        } catch (Exception e) {

        } finally {
            consumer.close();
        }

    }

}
