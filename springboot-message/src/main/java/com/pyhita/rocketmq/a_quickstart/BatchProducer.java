package com.pyhita.rocketmq.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Arrays;
import java.util.List;

public class BatchProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        producer.start();
        List<Message> msgs = Arrays.asList(
                new Message("batch-topic", "我是消息A".getBytes()),
                new Message("batch-topic", "我是消息B".getBytes()),
                new Message("batch-topic", "我是消息C".getBytes())
        );

        // 发送批量消息
        SendResult result = producer.send(msgs);
        System.out.println(result);
        // 关闭实例
        producer.shutdown();
    }
}
