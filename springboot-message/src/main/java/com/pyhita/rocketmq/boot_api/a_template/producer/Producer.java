package com.pyhita.rocketmq.boot_api.a_template.producer;

import com.pyhita.rocketmq.constant.TimeFormatter;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Producer implements InitializingBean {

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void afterPropertiesSet() throws Exception {
        sendDelay();
    }


    // 发送同步消息
    private void send() {
        SendResult result = template.syncSend("boot-topic", "这是boot整合程序");
//        template.send((org.springframework.messaging.Message<?>) message);
        System.out.println("result = " + result);
    }

    // 发送异步消息
    private void asyncSend() {
        template.asyncSend("boot-topic", "异步消息体", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("消息发送失败");
            }
        });
    }

    // 发送单向消息
    private void sendOneWay() {
        template.sendOneWay("boot-topic", "这是OneWay消息");
    }

    // 发送延迟消息
    private void sendDelay() {
        Message<String> msg = MessageBuilder.withPayload("延迟消息体").build();
        System.out.println("发送时间：" + LocalDateTime.now().format(TimeFormatter.DATE_TIME_FORMATTER));
        SendResult result = template.syncSend("boot-topic", msg, 2000, 3);
        System.out.println("result = " + result);
    }
}
