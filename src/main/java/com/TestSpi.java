package com;

import com.ly.LoggerService;

public class TestSpi {
    public static void main(String[] args) {
        LoggerService loggerService=LoggerService.getService();
        loggerService.info("hello");
        loggerService.debug("hello");
    }
}
