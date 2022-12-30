package com.xiaotu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo2 {
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException{
        MyNumber2 myNumber = new MyNumber2();
        CountDownLatch countDownLatch   = new CountDownLatch(SIZE);

        for (int i = 1; i <= SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= 1000 ; j++) {
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }

            },String.valueOf(i)).start();
        }


    /*    try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t result "+myNumber.atomicInteger.get());

    }
}

class MyNumber2 {
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}