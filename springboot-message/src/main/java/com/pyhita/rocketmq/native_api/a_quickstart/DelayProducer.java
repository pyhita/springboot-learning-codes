package com.pyhita.rocketmq.native_api.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import com.pyhita.rocketmq.constant.TimeFormatter;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.time.LocalDateTime;

public class DelayProducer {

    /**
     * 异步的发送消息，通过回调函数确认是否发送成功
     * @param args
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("delay-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        producer.start();
        Message message = new Message("delay-topic", "这是异步的消息".getBytes());
        // 发送消息，并且设置回调，处理消息发送成功或者失败
        message.setDelayTimeLevel(2);
        producer.send(message);

        System.out.println("消息发送时间： " + LocalDateTime.now().format(TimeFormatter.DATE_TIME_FORMATTER));
    }
}
