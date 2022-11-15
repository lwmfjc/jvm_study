package com.ly;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.util.concurrent.*;

@Data
class MyClass {
    private String name;
}

@Slf4j
public class LyTest {

    @Test
    public void test1() throws Exception {
       /* ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++) {
            executorService.submit(() -> {
                log.info("测试");
                //System.out.println("测试");
            });
        }
        executorService.shutdown();*/
        /*MyClass myClass=new MyClass();
        myClass.setName("name1");
        Callable<Object> callableTest = Executors.callable(() -> {
            //需要运行3秒
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("测试");
        },myClass);
        //运行线程
        Object call = callableTest.call();
        System.out.println(call);*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<MyClass> myClassCallable = new Callable<MyClass>() {
            @Override
            public MyClass call() throws Exception {
                MyClass myClass1 = new MyClass();
                myClass1.setName("ly-callable-测试");
                TimeUnit.SECONDS.sleep(2);
                return myClass1;
            }
        };
        Future<?> submit = executorService.submit(myClassCallable);
        //这里会阻塞
        Object o = submit.get();
        log.info("ly-callable-打印结果1:" + o);


        FutureTask<MyClass> futureTask = new FutureTask<>(() -> {
            MyClass myClass1 = new MyClass();
            myClass1.setName("ly-FutureTask-测试");
            TimeUnit.SECONDS.sleep(2);
            return myClass1;
        });
        Future<?> submit2 = executorService.submit(futureTask);
        //这里会阻塞
        Object o2 = submit2.get();
        log.info("ly-callable-打印结果2:" + o2);
        executorService.shutdown();
    }

    @Test
    public void test2() {
        ThreadPoolExecutor
                threadPoolExecutor;
        Executors.newFixedThreadPool(3);
        Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        Executors.newScheduledThreadPool(3, Executors.defaultThreadFactory());
    }

    class MyRejectPolicy extends ThreadPoolExecutor.CallerRunsPolicy{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            super.rejectedExecution(r, e);
        }
    }

    @Test
    public void test3() throws InterruptedException {
        log.info("当前线程名Main:{}",Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        RejectedExecutionHandler handler =new ThreadPoolExecutor.AbortPolicy();
        /*new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //log.info("当前线程名reject:{}",Thread.currentThread().getName());
                //新开一个线程处理任务
                new Thread(r).start();
            }
        };*/
        ThreadPoolExecutor threadPoolExecutor=
                new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), handler);
        for(int i=0;i<1000;i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("hello");
                log.info("运行任务：{}{}", finalI,Thread.currentThread().getName());
            });

        }
        threadPoolExecutor.shutdown();
        while (true)
        TimeUnit.SECONDS.sleep(3);
    }
}
