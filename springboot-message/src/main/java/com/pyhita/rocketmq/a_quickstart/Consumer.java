package com.pyhita.rocketmq.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {

    public static void main(String[] args) throws Exception {
        // 1 创建consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer");
        // 2 设置nameserv
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        // 订阅某个主题
        consumer.subscribe("test-topic", "*");
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
        // 3 启动consumer
        consumer.start();

        // 5 挂起当前主线程
        System.in.read();
    }
}
