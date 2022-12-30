package com.xiaotu.juc;

import java.util.ArrayList;

public class SynchronizedTest2  {
    public static void main(String[] args) throws InterruptedException{
        SynchronizedTest2 synchronized2 = new SynchronizedTest2();
        Data data = synchronized2.new Data();
        new Thread(new Runnable() {
            public void run() {
                data.insert();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                data.insert();
            }
        }).start();

        Thread.currentThread().sleep(3000);
        System.out.println("main thread end");
    }


    class Data {
        private ArrayList<Integer> arr = new ArrayList<Integer>();
        private int i = 0;
        public void insert(){
            System.out.println(Thread.currentThread().getName() + " 准备插入");
            for(;i<100;i++){
                synchronized (this){
                    if (i<100){
                        if (!arr.contains(i)){
                            System.out.println(Thread.currentThread().getName() + " 正在插入" + i);
                            arr.add(i);
                        }else {
                            System.out.println(Thread.currentThread().getName() + " 已包含" + i);
                        }
                    }
                }
            }
        }


    }


}
