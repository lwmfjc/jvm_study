package com.ly;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SemaphoreExample2 {
    /*public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    int i1 = atomicInteger.incrementAndGet();
                    log.info("获取一个通行证" + finalI);
                    TimeUnit.SECONDS.sleep(finalI + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log.info("通行证" + finalI + "释放完毕");
                    semaphore.release();
                }

            });
        }
        log.info("全部获取完毕");
        executorService.shutdown();
    }*/
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Semaphore semaphore = new Semaphore(3);
            executorService.submit(() -> {
                try {
                    //semaphore.acquire();
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //semaphore.release();
                }

            });
        log.info("全部获取完毕");
        executorService.shutdownNow();
        log.info("线程池shutdown");
    }
}
