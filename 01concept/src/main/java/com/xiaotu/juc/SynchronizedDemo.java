package com.xiaotu.juc;

//public class SynchronizedDemo {
//    public void method(){
//        synchronized (this){
//            System.out.println("synchronized 代码块");
//        }
//    }
//}
public class SynchronizedDemo {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}
class Ticket2 {
    //票数
    private int number = 100;

    //操作方法：卖票
    public synchronized void sale() {
        //判断：是否有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + (number--) + " " + number);
        }
    }
}
