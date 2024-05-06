package com.pyhita.rocketmq.native_api.i_query_msg;

import com.pyhita.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author: kante_yang
 * @Date: 2024/4/15
 */
public class QueryMessage {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        consumer.subscribe("test-topic", "*");
        consumer.start();

        MessageExt ext = consumer.viewMessage("test-topic", "fdsfsfsdfdsfdsfsd");
        System.out.println(ext);
    }

}
