package com.xiaotu.juc;

import org.omg.IOP.IOR;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    private static final int threadCount = 6;

    public static void main(String[] args) throws InterruptedException{
        //创建一个具有固定线程数的线程池对象
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount ; i++) {
            final int threadnum = i;
            threadPool.execute(()->{
                try {
                    //处理xxx

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");

    }


}
