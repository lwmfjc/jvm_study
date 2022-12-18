package com.ly;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Hello4 {
    public static void main(String[] args) throws InterruptedException {
        Unsafe unsafe;
        ThreadPoolExecutor threadPoolExecutor;
        AtomicInteger atomicInteger;
        TimeUnit.SECONDS.sleep(5);
        Customer customer = new Customer();
        new Thread(()->{
            customer.test();
        },"线程1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            customer.test();
        },"线程2").start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("两个线程都执行完毕"); //16字节
        System.out.println(ClassLayout.parseInstance(customer).toPrintable()); //16字节
    }
}
class Customer{
    private int a;
    private boolean b;
    synchronized public void test(){
        System.out.println(Thread.currentThread().getName()+"即将执行test()--5s");
        System.out.println(Thread.currentThread().getName()+"执行test()时的对象头信息：");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ClassLayout.parseInstance(this).toPrintable()); //16字节
        System.out.println(Thread.currentThread().getName()+"执行test()结束");
    }
}
