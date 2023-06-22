package com;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    log.info("将运行3s");
                    TimeUnit.SECONDS.sleep(3);
                    log.info("我进行等待");
                    obj.wait();//等待，释放锁
                    log.info("我回来了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(() -> {

            synchronized (obj) {
                try {
                    log.info("我在6秒后唤醒thread1");
                    TimeUnit.SECONDS.sleep(6);
                    obj.notify();//唤醒，但不会释放锁
                    log.info("我将继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "线程2").start();
    }

    /**
     * @param args   整数
     * @param result 结果数组
     * @param length 已经处理了几个数，进递归时为0，结束时为n-1
     * @return
     */
    static void solve(long args, long[] result, int length) {
        if (args == 0) {
            return;
        }
        result[length++] = args % 10;
        args = args / 10;
        solve(args, result, length);
    }
}
