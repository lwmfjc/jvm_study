package com.jvm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyMain {
    private byte[] x = new byte[10 * 1024 * 1024];//10M

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始循环--");
        int i=0;
        while (++i>0) {
            String a=new Date().toString();
            MyMain myMain = new MyMain();
            System.out.println(i+"循环中---" + new Date());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
