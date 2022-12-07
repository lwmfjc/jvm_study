package com.ly;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
        while (true){}
    }

    public static void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : ex.toString()+"world!";
        });
        String s = future.get();
        log.info(s);

    }

    public static void test1(){
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world!";
        });
        log.info("被阻塞啦----");
        stringCompletableFuture
                .whenComplete((s,e)->{
                    log.info("complete1----"+s);
                })
                .whenComplete((s,e)->{
                    log.info("complete2----"+s);
                })
                .thenAccept(s->{
                    log.info("打印结果"+s);
                })
                .thenRun(()->{
                    log.info("阻塞结束啦");
                });
        while (true){

        }
    }
}
