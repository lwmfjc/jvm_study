package com.ly;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownTest {
    /*public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.submit(()->{
                try {
                    log.info(finalI +"开始执行");
                    //执行3s
                    TimeUnit.SECONDS.sleep(3);
                    log.info(finalI +"执行结束");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        countDownLatch.await();
        log.info("await之后");
        executorService.shutdownNow();
        log.info("所有线程执行结束");
    }*/
    public static void main(String[] args) throws InterruptedException {
        final int count =10;
        CountDownLatch countDownLatch=new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            executorService.submit(()->{
                try {
                    log.info( "进入线程"+finalI);
                    countDownLatch.await();
                    log.info(finalI +"开始执行");
                    log.info(finalI +"执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        log.info("等待3s");
        TimeUnit.SECONDS.sleep(3);
        log.info("开始countDown");
        countDownLatch.countDown();
        executorService.shutdown();
        log.info("所有线程执行结束");
    }
}
