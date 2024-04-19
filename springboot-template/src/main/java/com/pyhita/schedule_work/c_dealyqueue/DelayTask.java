package com.pyhita.schedule_work.c_dealyqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayTask implements Delayed {

    //  设置任务执行时间
    private long executeTime;

    // 设置具体的任务
    private Runnable task;

    // 判断任务是否到期，<= 0 标明已过期，需要执行
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    // 用于优先级队列内部排序使用
    @Override
    public int compareTo(Delayed o) {
        return Long.compare(executeTime, ((DelayTask)o).executeTime);
    }

    public void run() {
        task.run();
    }
}
