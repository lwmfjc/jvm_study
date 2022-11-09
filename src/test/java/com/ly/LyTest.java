package com.ly;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LyTest {

    @Test
    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++) {
            executorService.submit(() -> {
                log.info("测试");
                //System.out.println("测试");
            });
        }
        executorService.shutdown();
    }
}
