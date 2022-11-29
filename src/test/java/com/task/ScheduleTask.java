package com.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleTask {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("hello world!");
            }
        }, 3, 5, TimeUnit.SECONDS);//10表示首次执行任务的延迟时间，5表示每次执行任务的间隔时间，Thread.sleep(10000);

        System.out.println("Shutting down executor...");
        TimeUnit.SECONDS.sleep(4);
        //线程池一关闭，定时器就不会再执行
        scheduledExecutorService.shutdown();
        while (true){}
    }
}
