package com.ly;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class Hello5 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        TimeUnit.SECONDS.sleep(5);
        //System.out.println(Integer.toHexString(o.hashCode()));
        synchronized (o){

        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable()); //16字节
    }
}

