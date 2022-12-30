package com.xiaotu.juc;


public class T1 extends Thread {
    public void run(){
        System.out.println("当前线程："+Thread.currentThread().getId());
        System.out.println("T1");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{

        },"t1");
        t1.start();
    }
}
