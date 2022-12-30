package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                if (isStop){
                    System.out.println(Thread.currentThread().getName()+"\t is stop被改为true,程序停止");
                    break;
                }
                System.out.println("t1 ---- hello volatile");
            }
        },"t1").start();

        try {
            TimeUnit.MICROSECONDS.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(()->{
           isStop = true;
        },"t2").start();

    }

}
