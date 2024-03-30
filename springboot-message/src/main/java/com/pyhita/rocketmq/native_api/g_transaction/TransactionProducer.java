package com.pyhita.rocketmq.native_api.g_transaction;

import com.pyhita.constant.MqConstant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author: kante_yang
 * @Date: 2024/3/27
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        // 接受RocketMQ 回调的监听器接口
        // 实现执行订单本地事务，commit, rollabck 回调查询逻辑
        TransactionListener transactionListener = new TransactionListenerImpl();

        // 事务消息生产者
        TransactionMQProducer transactionProducer = new TransactionMQProducer("transaction-producer");
        transactionProducer.setNamesrvAddr(MqConstant.MAC_NAME_SERVER);

        // 定义RocketMQ 回调线程池
        ExecutorService executorService = new ThreadPoolExecutor(
            2,
            5,
            100,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("test-thread");
                    return thread;
                }
            });

        transactionProducer.setExecutorService(executorService);
        transactionProducer.setTransactionListener(transactionListener);
        transactionProducer.start();

        Message message = new Message("pay-order-success-topic", "testTag", "testKey", "订单支付成功".getBytes());

        // 将消息作为half消息发送出去
        try {
            TransactionSendResult sendResult = transactionProducer.sendMessageInTransaction(
                message, null);
        } catch (Exception e) {
            // half 消息发送失败，执行订单回滚逻辑
        }
    }


}
