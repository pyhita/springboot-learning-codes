package com.pyhita.rocketmq.native_api.a_quickstart;

import com.pyhita.rocketmq.constant.MqConstant;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author: kante_yang
 * @Date: 2024/3/22
 */
public class PullConsumer {

    public static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("pull-consumer");
        consumer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test-topic","*");
        consumer.setAutoCommit(true);
        consumer.start();

        try {
            while (running) {
                // 拉取一批消息
                List<MessageExt> messageExts = consumer.poll();
                doSomeThing(messageExts);
            }
        } finally {
            consumer.shutdown();
        }
    }

    private static void doSomeThing(List<MessageExt> messageExts) {
        // 真正的业务处理
        System.out.printf("%s%n", messageExts);
    }

}
