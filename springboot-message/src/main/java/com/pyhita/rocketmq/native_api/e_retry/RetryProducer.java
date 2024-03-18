package com.pyhita.rocketmq.native_api.e_retry;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.UUID;

/**
 * 发送消息的时候 可以设定消息的key，这个是我们业务相关的数据，有我们自己保证唯一性
 */
public class RetryProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("retry-producer");
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        // 设置消息发送失败时候的 重试次数
        producer.setRetryTimesWhenSendFailed(2);
        producer.setRetryTimesWhenSendAsyncFailed(2);
        producer.start();
        Message message = new Message("retry-topic", "vip1", UUID.randomUUID().toString(), "这是key 测试".getBytes());
        producer.send(message);

        producer.shutdown();
    }
}
