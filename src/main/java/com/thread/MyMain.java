package com.thread;

import com.unsafe.UnsafeGet;
import lombok.Getter;
import sun.misc.Unsafe;

import java.util.Map;

public class MyMain {
    static String a="abc";
    public static void main(String[] args) {
        System.out.println(MyMain.a);
        Unsafe unsafe = UnsafeGet.reflectGetUnsafe();
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();
        while (true) {
            boolean flag = changeThread.isFlag();
            //unsafe.loadFence(); //加入读内存屏障
            if (flag) {
                System.out.println("detected flag changed");
                break;
            }
            synchronized (MyMain.class){

            }
        }
        System.out.println("main thread end");
    }
}

@Getter
class ChangeThread implements Runnable {
    /**
     * volatile
     **/
    boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("subThread change flag to:" + flag);
    }
}