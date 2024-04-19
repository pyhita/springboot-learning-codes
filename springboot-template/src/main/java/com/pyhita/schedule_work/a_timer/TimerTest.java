package com.pyhita.schedule_work.a_timer;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer 内部使用一个叫做 TaskQueue 的类存放定时任务，它是一个基于最小堆实现的优先级队列。
 * TaskQueue 会按照任务距离下一次执行时间的大小将任务排序，保证在堆顶的任务最先执行。
 * 这样在需要执行任务时，每次只需要取出堆顶的任务运行即可！
 */
public class TimerTest {

    public static void main(String[] args) {
        // 示例代码：
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
    }
}
