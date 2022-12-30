package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo2 {
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(()-> {
            while (true){
                if (atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName()+"\t atomicBoolean被改为true,程序停止");
                    break;
                }
                System.out.println("t1 ==== hello atomicBoolean");
            }
        },"t1").start();


        try {
            TimeUnit.MILLISECONDS.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();

    }


}
