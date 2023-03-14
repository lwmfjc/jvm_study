package com.concurrent;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Object obj1=new Object();
        Object obj2=new Object();
        new Thread(()->{
            synchronized(obj1){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){

                }

            }
        },"线程1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            synchronized(obj2){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1){

            }

        }

        },"线程2").start();
    }
}
