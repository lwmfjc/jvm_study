package com.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Priority

        SynchronousQueue<String> synchronousQueue
                =new SynchronousQueue<>();
        new Thread(()->{
            try {
                log.info("放入数据A");
                synchronousQueue.put("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("继续执行");
        },"子线程1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String poll = null;
            try {
                poll = synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(poll);
        },"子线程2").start();
    }
}
