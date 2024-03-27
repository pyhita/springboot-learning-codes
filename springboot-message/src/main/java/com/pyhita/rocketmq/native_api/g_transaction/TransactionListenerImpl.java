package com.pyhita.rocketmq.native_api.g_transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author: kante_yang
 * @Date: 2024/3/27
 */
public class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // half 消息发送成功，回调到这里，执行本地订单事务
        // 根据本地事务执行结果选择去 commit 事务或者 rollback事务
        try {
            // 执行本地事务成功
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            // 执行失败，进行回滚
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    // commit 或者 rollback消息发送失败，回调到这里
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        // 查询本地事务是否执行成功
        Integer status = 0;

        if (status != null) {
            switch (status) {
                case 0: return LocalTransactionState.UNKNOW;
                case 1: return LocalTransactionState.COMMIT_MESSAGE;
                case 3: return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
