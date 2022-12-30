package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;

public class InterruptDemo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"\t isInterrupted被改为true,程序停止");
                    break;
                }
                System.out.println("t1 ---- hello interrupt api");
            }
        },"t1");
        t1.start();

        System.out.println("---- t1的默认中断标志位："+t1.isInterrupted());

        try {
            TimeUnit.MICROSECONDS.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

//        new Thread(()->{
//            t1.interrupt();
//        },"t2").start();
        t1.interrupt();

    }

}
