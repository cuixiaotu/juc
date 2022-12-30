package com.xiaotu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShareTest {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i <10;i++){
                share.incr();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10;i++){
                share.decr();
            }
        },"B").start();
    }
}


class Share {
    private Integer number = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr(){
        try {
            lock.lock();//加锁
            while (number!= 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":incr:" + number);
            condition.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decr(){
        try {
            lock.lock();
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":decr:" + number);
            condition.signal();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}