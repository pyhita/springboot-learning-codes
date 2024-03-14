package com.pyhita.rocketmq.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {

    public static void main(String[] args) throws Exception {
        // 1 创建producer
        DefaultMQProducer producer = new DefaultMQProducer("test-producer");
        // 2 连接namesrv
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        // 3 启动producer
        producer.start();
        // 4 创建消息体
        Message message = new Message("test-topic", "你好，RocketMQ".getBytes());
        // 5 发送消息
        SendResult send = producer.send(message);
        System.out.println("send = " + send);

        // 6 关闭producer
        producer.shutdown();
    }
}
