package com.xiaotu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class BankAccount {

    String bankName = "CCB";
    //更新对象属性必须使用public
    public volatile int money = 0;

    public void add(){
        money++;
    }

    //因为对象的属性修改原子类都是抽象类 所以每次使用都必须
    //使用静态方法newUpdater()创建一个更新器，并且需要设置要更新的类和熟悉
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");
    //不加synchronized 保证高性能原子类
    public void transMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }

}

/*
* 以一种线程安全的方法操作非线程安全对象的某些字段
*
* 需求：
* 10个线程
* 每个线程转账1000
* 不使用synchronize 尝试使用AtomicIntegerFieldUpdater来实现
*
* */
public class AtomicIntegerFieldUpdaterDemo{
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= 1000; j++) {
                        //bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();

        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t result:" + bankAccount.money);
    }

}

