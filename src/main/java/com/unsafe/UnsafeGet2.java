package com.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class UnsafeGet2 {
    private Unsafe unsafe;

    public UnsafeGet2() {

        this.unsafe = UnsafeGet2.reflectGetUnsafe();
        ;
    }

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.print(e.getMessage()+ e);
            return null;
        }
    }


    public static void main(String[] args) {
        UnsafeGet2 unsafeGet2=new UnsafeGet2();
        Unsafe unsafe=unsafeGet2.unsafe;
        Thread mainThread = Thread.currentThread();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                //5s后唤醒main线程
                System.out.println("subThread try to unpark mainThread");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("park main mainThread");
        unsafe.park(false,0L);
        System.out.println("unpark mainThread success");
    }


}
