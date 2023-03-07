package com.jvm;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.ZipInputStream;

public class MyMain {
    private byte[] x = new byte[10 * 1024 * 1024];//10M

    public static void main(String[] args) throws Exception {
        DataInputStream dataInputStream;
        ZipInputStream sa
                ;
        InputStreamReader inputStreamReader;
        Callable<Object> abc = Executors.callable(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaa");//输出aaa
        }, "abcccc");//如果没有"abcccc"，则下面输出null
        FutureTask<Object> futureTask = new FutureTask<>(abc);
        /*new Thread(futureTask).start();
        Object o = futureTask.get();
        System.out.println("获取值："+o); //输出abc
        */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(futureTask);
        Future<?> future1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        });
        /*System.out.println(future.get());//输出null*/
        System.out.println(future1.get()); //输出hello
        //System.out.println(futureTask.get());//输出abcccc

        System.out.println("阻塞结束");
        executorService.shutdown();
    }
}
