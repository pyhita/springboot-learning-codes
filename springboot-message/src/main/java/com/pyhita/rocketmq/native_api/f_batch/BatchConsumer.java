package com.pyhita.rocketmq.native_api.f_batch;

import com.pyhita.constant.MqConstant;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author: kante_yang
 * @Date: 2024/3/22
 */
public class BatchConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("batch-consumer");
        consumer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        consumer.subscribe("batch-topic", "*");
        consumer.setPullBatchSize(100);
        consumer.setConsumeMessageBatchMaxSize(200);
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("待消费的消息条数 " + msgs.size());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.in.read();

    }

}
