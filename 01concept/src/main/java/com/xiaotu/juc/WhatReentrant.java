package com.xiaotu.juc;

public class WhatReentrant {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("第1次所获锁 ：" + this);
                    int index  = 1;
                    while (true){
                        synchronized (this) {
                            System.out.println("第"+(++index) + "次所获锁 ：" + this);
                        }
                        if (index == 10){
                            break;
                        }
                    }
                }
            }
        }).start();
    }
}
