package com;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    log.info("运行中");
                    TimeUnit.SECONDS.sleep(3);
                    log.info("3s后释放锁");
                    obj.wait();//会释放锁
                    log.info("完成执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        //保证线程2在线程1之后启动
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (obj) {
                log.info("获得锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("5s后唤醒线程1");
                obj.notify();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("完成执行");
            }
        }, "线程2").start();
    }
}
