package com.pyhita.rocketmq.boot_api.a_template.consumer;

import com.pyhita.constant.TimeFormatter;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RocketMQMessageListener(consumerGroup = "boot-consumer", topic = "boot-topic")
public class ConsumerListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt msg) {

        // 收到消息 之后进行处理
        System.out.println("收到消息时间：" + LocalDateTime.now().format(TimeFormatter.DATE_TIME_FORMATTER));
        System.out.println("收到消息：http://137.125.248.1:8080/" + new String(msg.getBody()));
    }
}
