package com.pyhita.rocketmq.native_api.c_tag;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class TagProducer {

    public static void main(String[] args) throws Exception {

        // 测试发送的消息 可以有tag
        DefaultMQProducer producer = new DefaultMQProducer("tag-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        producer.start();
        Message msg1 = new Message("tag-topic", "vip1", "vip1的文章".getBytes());
        Message msg2 = new Message("tag-topic", "vip2", "vip2的文章".getBytes());

        producer.send(msg1);
        producer.send(msg2);

        producer.shutdown();
    }
}
