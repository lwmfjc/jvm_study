package com.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
/**
 * 测试五个线程等待
 */
public class TestCountDownLatch {
    private final int a = 974;
    private static int b = 974;
    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(5);
        new Thread(()->{
            try {
                countDownLatch.await();
                log.info("栅栏被推开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程A").start();
        new Thread(()->{
            try {
                countDownLatch.await();
                log.info("栅栏被推开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程B").start();
        new Thread(()->{
            try {
                countDownLatch.await();
                log.info("栅栏被推开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程C").start();
        new Thread(()->{
            try {
                countDownLatch.await();
                log.info("栅栏被推开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程D").start();
        new Thread(()->{
            try {
                countDownLatch.await();
                log.info("栅栏被推开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程E").start();
        new Thread(()->{
                //countDownLatch.await();
            for(int i=0;i<5;i++) {
                //每隔一秒钟推开一个栅栏
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("推开一个栅栏");
                countDownLatch.countDown();
            }

        },"线程F").start();
        while (true){}
    }
}
