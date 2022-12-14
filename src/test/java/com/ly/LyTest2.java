package com.ly;

import com.sun.jmx.snmp.tasks.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class LyTest2 {

    @Test
    public void a(){

    }
    @Test
    public void myTest() throws InterruptedException {
        final int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    log.info("进入线程" + finalI);
                    countDownLatch.await();
                    log.info(finalI + "开始执行");
                    log.info(finalI + "执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        log.info("等待3s");
        log.info("等待countDown");
        TimeUnit.SECONDS.sleep(3);
        log.info("开始countDown");
        countDownLatch.countDown();
        executorService.shutdown();
        log.info("所有线程执行结束");
    }

    @Test
    public void barrieTest() throws InterruptedException, TimeoutException, BrokenBarrierException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {

            int finalI = i;
            executorService.submit(()->{
                try {
                    log.info("线程{}进入休眠", finalI);
                    TimeUnit.SECONDS.sleep(5);
                    //如果Xs还没执行完毕，则不再等待
                    cyclicBarrier.await(10,TimeUnit.SECONDS);
                    log.info("线程{}休眠结束", finalI);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.info("关闭线程池");
    }

    @Test
    public void cc(){
        Map<String, Object> a=new HashMap<>();
        a.put("a",123);
        a.put("b",333);
        System.out.println(a.toString());
    }
}
