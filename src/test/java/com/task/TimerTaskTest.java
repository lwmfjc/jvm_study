package com.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        log.info("hello");
    }
}
public class TimerTaskTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);//控制小时
        calendar.set(Calendar.MINUTE, 1);//控制分钟
        calendar.set(Calendar.SECOND, 0);//控制秒
        Date time = calendar.getTime();//执行任务时间为17:01:00

        //每天定时17:02执行操作，每5秒执行一次
        timer.schedule(new MyTimerTask(), time, 5000 );
    }
}
