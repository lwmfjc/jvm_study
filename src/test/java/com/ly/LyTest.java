package com.ly;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

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
    @Test
    public void ss() throws InterruptedException {
        //boolean matches = Pattern.matches("^(.+)\\d{8}$", "18681500802");
        boolean matches = Pattern.matches("^a.+b$", "a123b");
        System.out.println(matches);
        /*String a="123";
        String format = String.format("lalala%sabc", a);
        System.out.println(format);
        AtomicInteger atomicInteger=new AtomicInteger();
        atomicInteger.incrementAndGet();
        A a1 = new A();
        a1.setA(2);
        a1.setB(10);
        A a2 = new A();
        a2.setA(4);
        a2.setB(5);
        A a3 = new A();
        a3.setA(6);
        a3.setB(7);
        AtomicReference<A> aAtomicReference = new AtomicReference<>(a1);
        a1.setA(333);
        aAtomicReference.compareAndSet(a1,a3);
        System.out.println(aAtomicReference.get());*/

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("暂停5s");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("暂停3s");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();
        log.info("主线程执行");
        ScheduledThreadPoolExecutor a;

    }

    @Test
    public void threadPoolTest(){
        /*boolean matches = Pattern.matches("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", "15811829505");
        System.out.println("手机号"+matches);
        String verCode = new Random().nextInt(9000)+1000+"";
        System.out.println(verCode);*/
        /*ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,3,3L,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        threadPoolExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });*/
        //Executors.newFi
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                log.info("执行任务");
            }
        });
        scheduledExecutorService.shutdown();
    }


    private String timeToDate(Long lt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSS");
        Date date = new Date(lt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * （1）System.currentTimeMillis 返回的毫秒，这个毫秒其实就是自1970年1月1日0时起的毫秒数.
     *
     * （2）java 中 System.nanoTime() 返回的是纳秒，nanoTime 而返回的可能是任意时间，甚至可能是负数
     */
    @Test
    public void timeTest() {
        long time1 = System.currentTimeMillis();
        long time2 = System.currentTimeMillis();
        log.info("time1#{} time2#{}", timeToDate(time1), timeToDate(time2));
        long nanot1 = System.nanoTime();
        long nanot2 = System.nanoTime();
        log.info("nanot1#{}---nanot2#{}", timeToDate(nanot1), timeToDate(nanot2));
    }
    @Test
    public void tt() throws InterruptedException {
        Lock reLock=new ReentrantLock(false);
        //reLock.lock();
        for(int i=0;i<100;i++){
            int finalI = i;
            new Thread(()->{
                reLock.lock();
                try {
                    log.info("线程标志"+finalI+"即将停止10s");
                    TimeUnit.SECONDS.sleep(10);
                    log.info("线程标志"+finalI+"停止结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reLock.unlock();
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
        while (true){}
    }
}
