package com.concurrent;
import org.apache.log4j.helpers.ThreadLocalMap;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestLy {

    //如果加上volatile,就能保证可见性，线程1 才能停止
      boolean stop = false;//对象属性

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Executors.newFixedThreadPool(3);
        AtomicInteger atomicInteger=new AtomicInteger(12);
        //int i = atomicInteger.incrementAndGet();
        /*FutureTask<String> futureTask=new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("等3s再把结果给你");
                TimeUnit.SECONDS.sleep(3);
                return "hello world";
            }
        });
        new Thread(futureTask).start();
        String s = futureTask.get();
        System.out.println("3s后获取到了结果"+s);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("abc");
            }
        }).start();*/
        /*ThreadLocal<String>  threadLocal =ThreadLocal.withInitial(()->"abc");
        threadLocal.set("sssa");
        threadLocal.remove();
        Thread thread;
        System.out.println(new SimpleDateFormat().toPattern());*/
       /*TestLy atomicTest = new TestLy();
        new Thread(() -> {
            while (!atomicTest.stop) {
                //这里不能加System.out.println ,因为这个方法内部用了synchronized修饰,会导致获取主内存的值，
                //就没法展示效果了
                *//*System.out.println("1还没有停止");*//*
            }
            System.out.println(Thread.currentThread().getName()+"停止了");
        },"线程1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicTest.stop= true;
            System.out.println(Thread.currentThread().getName()+"让线程1停止");
        },"线程2").start();
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        condition.await();
        condition.signal();
        while (true){}*/
    }


}
