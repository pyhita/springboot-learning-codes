package com.pyhita.rocketmq.native_api.c_tag;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer1 {

    public static void main(String[] args) throws Exception {
        // 仅仅消费 tag：vip1
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("vip1-consumer");
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        consumer.subscribe("tag-topic", "vip1");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("消息体是：" + new String(list.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.in.read();
    }
}
