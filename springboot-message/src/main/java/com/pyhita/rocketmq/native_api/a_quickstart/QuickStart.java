package com.pyhita.rocketmq.native_api.a_quickstart;

import com.pyhita.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

public class QuickStart {
    @Test
    public static void sendMessage() throws Exception {
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

    @Test
    public static void consumeMessage() throws Exception {
        // 1 创建consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer");
        // 2 设置nameserv
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        // 3 启动consumer
        consumer.start();
        // 4 设置回调函数，处理收到的消息
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt ext = list.get(0);
                String content = new String(ext.getBody());
                System.out.println("content = " + content);
                // 返回消息消费成功 ack
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 5 挂起当前主线程
        System.in.read();
    }
}
