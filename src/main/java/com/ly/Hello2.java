package com.ly;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hello2 {
    public static void main(String[] args) throws InterruptedException {
        ClassLoader classLoader;
        //开启偏向锁
        TimeUnit.SECONDS.sleep(5);
        //这个对象的创建必须在4s延迟之后,即偏向锁开启后再创建对象才能体现出来
        Object o = new Object();
        //System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 不论没有加synchronized,如果调用了hashCode()那最后三位就是001，没有调用就是101
        System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("不可偏向之后");
        new Thread(()->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        Lock lock=new ReentrantLock();
        lock.lock();

        lock.unlock();
    }
}
