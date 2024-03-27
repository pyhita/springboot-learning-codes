package com.pyhita.rocketmq.native_api.f_batch;

import com.pyhita.rocketmq.constant.MqConstant;
import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author: kante_yang
 * @Date: 2024/3/22
 */
public class BatchProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("batch-producer");
        producer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        producer.start();

        List<Message> msgs = new ArrayList<>();
        for (int i = 0; i < 100 * 10; i++) {
            msgs.add(new Message("batch-topic", ("第" + i + "条消息！").getBytes()));
        }

        SendResult send = producer.send(msgs);
        System.out.println("send = " + send);
    }

}
