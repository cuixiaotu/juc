package com.xiaotu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LookSupportDemo2 {
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": come in "+ System.currentTimeMillis() );
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ": 被唤醒 " + System.currentTimeMillis());
        },"t1");
        t1.start();
        //暂停
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t: 发出通知 "+ System.currentTimeMillis() );
        },"t2").start();


    }
}
