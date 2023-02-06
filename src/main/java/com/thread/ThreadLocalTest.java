package com.thread;

import lombok.Data;

import java.util.concurrent.CountDownLatch;

@Data
class LyTest{
    private ThreadLocal<String> threadLocal=ThreadLocal.withInitial(()->{
        return "hello";
    });
}
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);


        LyTest lyTest=new LyTest();
        ThreadLocal<String> threadLocal = lyTest.getThreadLocal();
        new Thread(()->{
            String name = Thread.currentThread().getName();
            threadLocal.set(name+ "-ly");
            System.out.println(name+"：threadLocal当前值"+threadLocal.get());
            countDownLatch.countDown();
        },"线程1").start();
        new Thread(()->{
            String name = Thread.currentThread().getName();
            threadLocal.set(name+ "-ly");
            System.out.println(name+"：threadLocal当前值"+threadLocal.get());
            countDownLatch.countDown();
        },"线程2").start();
        /*while (true){}*/
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"：threadLocal当前值"+threadLocal.get());


    }
}
