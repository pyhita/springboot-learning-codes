package com.pyhita.rocketmq.native_api.b_sequence;

import com.pyhita.constant.MqConstant;
import com.pyhita.rocketmq.entity.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Arrays;
import java.util.List;

/**
 * 消息乱序的原因：一个topic里面会有多个message queue，
 * 所以消息可能发送到不同queue中，多线程消费的时候导致消息乱序
 */
public class OrderProduer {
    private static List<Order> orders = Arrays.asList(
            new Order(1, "下单"),
            new Order(1, "短信"),
            new Order(1, "物流"),
            new Order(2, "下单"),
            new Order(2, "短信"),
            new Order(2, "物流")
    );

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("order-producer");
        producer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        producer.start();
        for (Order order : orders) {
            // 转成Message 对象
            Message msg = new Message("order-topic", order.toString().getBytes());
            // 发送时，进行顺序发送，保证订单号相同的消息 打在同一个queue
            producer.send(msg, (list, m, obj) -> {
                Integer orderSqn = (Integer) obj;
                return list.get(orderSqn % list.size());
            }, order.getOrderSqn());
        }

        producer.shutdown();
    }
}
