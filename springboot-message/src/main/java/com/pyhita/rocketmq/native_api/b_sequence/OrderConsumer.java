package com.pyhita.rocketmq.native_api.b_sequence;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class OrderConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order-consumer");
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        consumer.subscribe("order-topic", "*");
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                System.out.println("当前线程 ID: " + Thread.currentThread().getId());
                System.out.println(new String(list.get(0).getBody()));

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }
}
