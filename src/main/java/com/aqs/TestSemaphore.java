package com.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);//能同时运行3个

        for (int i = 0; i < 15; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();//获取通行证
                    System.out.println(Thread.currentThread().getName() + "执行中...");
                    TimeUnit.SECONDS.sleep(finalI);
                    System.out.println(Thread.currentThread().getName() + "释放了通行证");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"线程"+finalI).start();
        }
    }
}
