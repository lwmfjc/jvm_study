package com.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue
                = new ArrayBlockingQueue<>(2);
        for (int i = 10; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.put(finalI + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()
                        .getName() + "放入了元素[" + finalI + "");
            }, "线程" + i).start();
        }
        for (int i = 20; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String remove = null;
                try {
                    remove = blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()
                        .getName() + "取出了元素[" + remove + "");
            }, "线程" + i).start();
        }
    }
}
