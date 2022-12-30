package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
  //  static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(() ->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号：" + stamp);
            try { TimeUnit.MILLISECONDS.sleep(500); }catch (InterruptedException e) { e.printStackTrace(); }


            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 2次版本号：" + stampedReference.getStamp());

            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 3次版本号：" + stampedReference.getStamp());

        },"t3").start();



        new Thread(() ->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号：" + stamp);
            try { TimeUnit.MILLISECONDS.sleep(1000); }catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = stampedReference.compareAndSet(100,2022,stamp,stamp+1);

            //try { TimeUnit.MILLISECONDS.sleep(200); }catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(b + "\t " + stampedReference.getReference() + "\t ：" + stampedReference.getStamp());


        },"t2").start();

    }

}
