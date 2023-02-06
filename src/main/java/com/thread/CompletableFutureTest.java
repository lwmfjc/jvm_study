package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*CountDownLatch countDownLatch = new CountDownLatch(2);
        //相当于使用了一个线程池，开启线程，提交了任务
        CompletableFuture<Object> a = CompletableFuture.supplyAsync(() -> {
            System.out.println("a");
            //执行了3s的任务
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            return "a-hello";
        });
        a.thenApply((aResult) -> {
            //在上面的任务结束后才会打印
            System.out.println(aResult);
            return "hello-new";
        }).thenAccept((aResultNew)->{
            System.out.println(aResultNew);
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            System.out.println("b");
            //执行了3s的任务
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println("执行完毕");//3s后会执行
*/

        /*CompletableFuture<String> future
                = CompletableFuture

                .supplyAsync(() -> "hello!") //supplyAsync产生结果;runAsync不产生结果
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        System.out.println(future.get());*/

        /*CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行了第1个");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第1个执行结束啦");
            return "hello!";
        })
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> {
                            System.out.println("执行了第2个");
                            System.out.println("第2个执行结束啦");
                            return "world!";
                        }), (s1, s2) -> s1 + s2);
        System.out.println(completableFuture.get());*/

        CompletableFuture<Object> a = CompletableFuture.supplyAsync(() -> {
            System.out.println("a");
            //执行了3s的任务
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "a-hello";
        });
        CompletableFuture<Object> b = CompletableFuture.supplyAsync(() -> {
            System.out.println("b");
            //执行了3s的任务
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "b-hello";
        });

        /*
        //会等两个任务都执行完才继续
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(a, b);
        voidCompletableFuture.join();
        //停顿10s
        System.out.println("主线程继续执行");*/

        //任何一个任务执行完就会继续执行
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(a, b);
        objectCompletableFuture.join();
        //会得到最快返回值的那个CompletableFuture的值
        System.out.println(objectCompletableFuture.get());
        //停顿3s
        System.out.println("主线程继续执行");
    }
}
