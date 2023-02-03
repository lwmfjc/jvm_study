package com.jvm;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MyMain {
    private byte[] x = new byte[10 * 1024 * 1024];//10M

    public static void main(String[] args) throws Exception {
        Callable<Object> abc = Executors.callable(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("abc");
        },"abcccc");//如果没有"abcccc"，则下面输出null
        FutureTask<Object> futureTask = new FutureTask<>(abc);
        new Thread(futureTask).start();
        Object o = futureTask.get();
        System.out.println("获取值："+o);
    }
}
