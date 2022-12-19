package com.jvm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始循环--");
        while (true){
            System.out.println("循环中---"+new Date());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
