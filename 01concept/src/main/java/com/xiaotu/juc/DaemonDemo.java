package com.xiaotu.juc;

import java.util.concurrent.TimeUnit;

public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+"\t 开始运行"+(Thread.currentThread().isDaemon() ? "守护进程" : "用户进程"));
            while (true){

            }
        },"t1");
        //t1.setDaemon(true);
        t1.start();
        //3秒后主线程再启动
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("主线程运行完毕");
    }

}

