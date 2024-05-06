package com.pyhita.rocketmq.native_api.h_delay;

import com.pyhita.constant.MqConstant;
import com.pyhita.constant.TimeFormatter;
import java.time.LocalDateTime;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class DelayProducer {

    /**
     * 异步的发送消息，通过回调函数确认是否发送成功
     * @param args
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("delay-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        producer.start();
        Message message = new Message("delay-topic", "这是延时的消息".getBytes());
        // 发送消息 设置消息的延迟级别
        message.setDelayTimeLevel(2);
        producer.send(message);

        System.out.println("消息发送时间： " + LocalDateTime.now().format(TimeFormatter.DATE_TIME_FORMATTER));
    }
}
