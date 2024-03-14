package com.pyhita.rocketmq.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class OneWayProducer {

    /**
     * 异步的发送消息，通过回调函数确认是否发送成功
     * @param args
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("oneway-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        producer.start();
        Message message = new Message("oneway-topic", "这是异步的消息".getBytes());
        // 发送消息，并且设置回调，处理消息发送成功或者失败
        producer.sendOneway(message);

        System.in.read();
    }
}
