package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;

//synchronized 时期 使用同一把锁锁同一个对象
//线程1 objectLock.wait();
//线程2 objectLock.notify();
public class LookSupportDemo {
    public static void main(String[] args) {
        Object objectLook = new Object();

        new Thread(()->{
            synchronized (objectLook) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": come in" );
                try {
                    objectLook.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": 被唤醒" );
            }
        },"t1").start();

/*      try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(()->{
            synchronized (objectLook){
                objectLook.notify();
                System.out.println(Thread.currentThread().getName() + "：发出通知");
            }
        },"t2").start();

    }

}
