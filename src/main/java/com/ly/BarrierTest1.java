package com.ly;

import java.util.concurrent.*;

public class BarrierTest1 {
    public static void main(String[] args) throws InterruptedException, TimeoutException, BrokenBarrierException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                cyclicBarrier.await( );
                System.out.println("数量11===="+cyclicBarrier.getNumberWaiting());
                System.out.println("111");
            } catch (Exception e) {
                System.out.println("数量异常1111==="+cyclicBarrier.getNumberWaiting());
                // e.printStackTrace();
                System.out.println("报错1");
            }

        });
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("数量2222===="+cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await(111,TimeUnit.SECONDS);
                System.out.println("222");
            } catch (Exception e) {
                System.out.println("数量异常2222===="+cyclicBarrier.getNumberWaiting());
                System.out.println("报错2");
            }
        });
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("数量33 await前===="+cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                System.out.println("数量33 await后===="+cyclicBarrier.getNumberWaiting());
                System.out.println("333");
            } catch (Exception e) {
                System.out.println("数量异常333===="+cyclicBarrier.getNumberWaiting());
                System.out.println("报错3");
            }
        });
    }
}
