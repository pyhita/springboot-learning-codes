package com.pyhita.rocketmq.native_api.a_quickstart;

import com.pyhita.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class PushConsumer {

    public static void main(String[] args) throws Exception {
        // 1 创建consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("push-consumer");
        // 2 设置nameserv
        consumer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
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
