package com.test;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0   + "KB");    //系统的最大空间-Xmx--运行几次都不变
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0   + "KB");  //系统的空闲空间--每次运行都变
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0   + "KB");  //当前可用的总空间 与Xms有关--运行几次都不变
        byte[] b = new byte[1 * 1024 * 1024];
        System.out.println("分配了1M空间给数组");
        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");  //系统的最大空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");  //系统的空闲空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

        byte[] c = new byte[10 * 1024 * 1024];
        System.out.println("分配了10M空间给数组");
        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");  //系统的最大空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");  //系统的空闲空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");  //当前可用的总空间

        b=null;
        c=null;
        System.gc();
        System.out.println("进行了gc");

        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");    //系统的最大空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");  //系统的空闲空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");  //当前可用的总空间


    }
}
