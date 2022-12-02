package com.ly;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
@Slf4j
public class BarrierTest {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {

            int finalI = i;
            executorService.submit(()->{
                try {
                    log.info("线程{}进入休眠", finalI);
                    TimeUnit.SECONDS.sleep(finalI);
                    log.info("线程{}休眠结束", finalI);
                    //如果Xs还没执行完毕，则抛出异常(如果)
                    /*
                        Waits until all  have invoked on this barrier, or the specified waiting time elapses
                     */
                    //有个问题，是2s后还没到达，就抛异常；还是放开超过了2s 抛异常
                    cyclicBarrier.await(2,TimeUnit.SECONDS);
                    log.info("线程{}执行", finalI);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.info("关闭线程池");
    }
}
