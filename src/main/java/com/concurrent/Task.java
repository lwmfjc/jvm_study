package com.concurrent;

public class Task implements Runnable {

    boolean running = true;
    int i = 0;

    @Override
    public void run() {
        while (running) {
            //System.out.println(...); 加入这行代码后可以跳出无限循环
            i();
        }
    }

    public int i(){
        return i++;
    }

    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread th = new Thread(task);
        th.start();
        Thread.sleep(10);
        task.running = false;
        Thread.sleep(100);
        System.out.println(task.i);
        System.out.println("程序停止");
    }
}
