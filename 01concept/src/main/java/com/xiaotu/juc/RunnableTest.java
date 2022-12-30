package com.xiaotu.juc;

public class RunnableTest {

    public static void main(String[] args) {
        R1 runnable01 = new R1();
        new Thread(runnable01).start();
    }

    public static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getId());

            System.out.println("R1");
        }
    }
}
