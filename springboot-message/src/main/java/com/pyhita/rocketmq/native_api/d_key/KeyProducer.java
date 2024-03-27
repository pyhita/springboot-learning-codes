package com.pyhita.rocketmq.native_api.d_key;

import com.pyhita.rocketmq.constant.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.UUID;

/**
 * 发送消息的时候 可以设定消息的key，这个是我们业务相关的数据，有我们自己保证唯一性
 */
public class KeyProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("key-producer");
        producer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        producer.start();
        String key = UUID.randomUUID().toString();
        System.out.println("key = " + key);
        Message message = new Message("key-topic", "vip1", key, "这是key 测试".getBytes());
        producer.send(message);

        producer.shutdown();
    }
}
