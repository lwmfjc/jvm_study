package com;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Main2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello";
            }
        });
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "world";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> submit1 = executorService.submit(futureTask);//注意，这里执行的是submit(Runnable r);
        Future<String> submit2 = executorService.submit(callable);//这里执行的是submit(Callable c);
        executorService.shutdown();
        Object obj = submit1.get();//这里obj为null//阻塞3s
        log.info(String.valueOf(obj == null));
        //阻塞5-3=2s
        // 如果是Executors.newSingleThreadExecutor();则会等上一个任务阻塞结束，才有线程可以执行
        log.info(submit2.get());

    }
}
