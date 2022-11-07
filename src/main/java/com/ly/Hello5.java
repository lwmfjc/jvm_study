package com.ly;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
class HelloA{

    synchronized public void say(){
        System.out.println("synchronized时--start");
        System.out.println(ClassLayout.parseInstance(this).toPrintable()); //16字节
        System.out.println("当前线程id(16进制):"+Long.toHexString(Thread.currentThread().getId() ));
        System.out.println("synchronized时--end");
    }
}
public class Hello5 {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        HelloA helloA = new HelloA();
        System.out.println("synchronized前");
        System.out.println(ClassLayout.parseInstance(helloA).toPrintable()); //16字节
        helloA.say();
        System.out.println("synchronized后");
        System.out.println(ClassLayout.parseInstance(helloA).toPrintable()); //16字节
    }
}

