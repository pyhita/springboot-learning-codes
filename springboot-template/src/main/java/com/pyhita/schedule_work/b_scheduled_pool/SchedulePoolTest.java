package com.pyhita.schedule_work.b_scheduled_pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */
public class SchedulePoolTest {

    public static void main(String[] args) {

        // 创建一个ScheduledExecutorService实例，并设置线程池的大小为5
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        // 提交任务到线程池
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        }, 3, 1, TimeUnit.SECONDS);

    }
}
