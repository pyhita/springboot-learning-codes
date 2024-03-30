package com.pyhita.rocketmq.native_api.e_retry;

import com.pyhita.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

@Slf4j
public class RetryConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("retry-consumer");
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        consumer.subscribe("retry-topic", "vip1");
        // 设置重试次数
        consumer.setMaxReconsumeTimes(2);
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("消息重试次数 retry ：" + list.get(0).getReconsumeTimes());
                // 超过重试阈值之后 消息被发送给 死信队列，死信队列的topic 是%DQ%retry-consumer
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
       consumer.setMessageListener(new MessageListenerConcurrently() {
           @Override
           public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
               try {
                   // 执行消费的代码
                   System.out.println("消息体：" + new String(list.get(0).getBody()));
                   // 制造一个异常，表示消息消费失败
                   int i = 1 / 0;
               } catch (Exception e) {
                   log.error("消息消费失败， 消息Id是：{}", list.get(0).getKeys());
                   // 出现问题判断重试的次数
                   MessageExt ext = list.get(0);
                   int retry = ext.getReconsumeTimes();
                   System.out.println("retry = " + retry);

                   if (retry >= 3) {
                       // 将消息确认了，存在DB 中进行后续处理
                       return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                   } else {
                       // 进行消息重试
                       return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                   }
               }

               return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
           }
       });
        consumer.start();

        System.in.read();
    }
}
