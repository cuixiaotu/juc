package com.xiaotu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableTest {
    //实现Callable接口 + FutureTask (可以拿到返回结果，可以处理异常)
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();

        Integer integer = null;
        try {
            integer = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(integer);
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程："+Thread.currentThread().getId());
            System.out.println("C1");
            return 66;
        }
    }

}
