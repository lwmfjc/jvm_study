package com.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {
    public static void main(String[] args) {
         CountDownLatch countDownLatch=new CountDownLatch(3);
         new Thread(()->{
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(Thread.currentThread().getName()+"执行完毕");
             countDownLatch.countDown();
         },"线程1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
            countDownLatch.countDown();
        },"线程2").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
            countDownLatch.countDown();
        },"线程3").start();
        try {
            System.out.println(Thread.currentThread().getName()+"等待中....");
            countDownLatch.await();//阻塞
            System.out.println(Thread.currentThread().getName()+"等待完毕，继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
