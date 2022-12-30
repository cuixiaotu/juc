package com.xiaotu.juc;

public class JoinDemo2 {
    public static void main(String[] args) {
        Thread t = new Thread(()-> System.out.println("test"));
        t.start();
        System.out.println("main1");
        try {
            t.join();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main2");
    }
}
