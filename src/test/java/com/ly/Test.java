package com.ly;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    @org.junit.Test
    public void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{

        });
        executorService.shutdown();
    }
}
