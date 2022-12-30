package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static final int SIZE = 50;

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= SIZE; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    myNumber.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\t result "+myNumber.atomicInteger.get());

    }


}

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}