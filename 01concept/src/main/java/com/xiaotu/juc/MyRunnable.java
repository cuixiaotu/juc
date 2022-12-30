package com.xiaotu.juc;

import java.util.Date;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyRunnable implements Runnable {
    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " Start .Time =" + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName()+ " End .Time =" + new Date());
    }


    public void processCommand() {
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return this.command;
    }
}
