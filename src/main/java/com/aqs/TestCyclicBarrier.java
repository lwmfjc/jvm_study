package com.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行咯");
        });
        for (int n = 0; n < 15; n++) {
            int finalN = n;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalN);
                    System.out.println(Thread.currentThread().getName() + "数据都准备好了,等待中....");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "出发咯!");
            }, "线程" + n).start();
        }
    }
}
