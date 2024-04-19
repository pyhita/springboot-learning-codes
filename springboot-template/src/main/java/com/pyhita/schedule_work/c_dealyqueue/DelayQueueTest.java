package com.pyhita.schedule_work.c_dealyqueue;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */
public class DelayQueueTest {

    @SneakyThrows
    public static void main(String[] args) {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        Thread.sleep(1000);
        long now = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            delayQueue.offer(new DelayTask(now + 1000 * i, () -> {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }));
        }

        while (!delayQueue.isEmpty()) {
            DelayTask task = delayQueue.take();
            if (task != null) {
                task.run();
            }
        }

    }

}
