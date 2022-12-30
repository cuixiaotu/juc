package com.xiaotu.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //test1();
        test2();
//        test3();

    }

    public static void test1(){
        Long sum = 0L;
        Long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_000_000; i++) {
            sum += i;
        }
        long end  = System.currentTimeMillis();
        System.out.println("sum = "+ sum + "，时间：  "+ (end - start));
    }

    //forkjoin
    public static void test2() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(1L,10_000_000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum =  submit.get();

        Long end = System.currentTimeMillis();
        System.out.println("sum = "+ sum + "，时间：  "+ (end - start));
    }

    //stream
    public static void test3(){
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L,10_000_000L).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = "+ sum + "，时间：  "+ (end - start));

    }

}
