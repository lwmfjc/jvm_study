package com.logger;

import com.ly.Logger;

public class LyLogger implements Logger {
    @Override
    public void info(String s) {
        System.out.println("hello,ly-info:"+s);
    }

    @Override
    public void debug(String s) {
        System.out.println("hello,ly-debug:"+s);

    }
}
