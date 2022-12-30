package com.xiaotu.juc;

public class VolatileExample {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        for(;;){
            if (myThread.isFlag()){
                System.out.println("主线程访问到了flag变量");
            }
        }
    }
}

class MyThread extends Thread {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //修改变量值
        flag = true;
        System.out.println("flag: " + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
