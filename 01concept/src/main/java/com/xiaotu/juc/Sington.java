package com.xiaotu.juc;


//1.懒汉式 （需要的时候才实例化 多线程并行getInstance的时候 会创建多个实例）
/*public class Sington {
    private static Sington instance;
    private Sington(){}

    public static Sington getInstance(){
        if (instance == null){
            instance = new Sington();
        }
        return instance;
    }

}*/

//2.同步版 getInstance设置为同步方法，线程安全并解决了多实例的问题，但是同一时刻只有一个线程能调用getInstance方法，效率低下。
// 原因 只有第一次获取的时候需要同步 对象实例化后可并行获取单例
/*
public class Sington {
    private static Sington instance;
    private Sington(){}

    public static synchronized Sington getInstance(){
        if (instance == null){
            instance = new Sington();
        }
        return instance;
    }
}
*/

//3.双重检验锁 double checked locking pattern
// instance ==null 第一次在同步代码外 可能多线程进入if逻辑里面
// 获取到锁的第一个实例化对象  后面的进程等待后 直接返回已实例化的对象

// 为啥需要volatile
// 1.为uniqueInstance分配内存空间
// 2.初始化uniqueInstance
// 3.将uniqueInstance指向分配的内存地址
// 由于JVM的指令重排，执行顺序可能变为1->3->2.指令重排再单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例，
// 例如，线程T1执行了1和3，此时T2调用了getInstance发现instance不为空，因此返回instance，但此时instance还未被初始化。
public class Sington{
    private volatile static Sington uniqueInstance;
    private Sington(){}

    public static Sington getInstance(){
        if(uniqueInstance == null){
            synchronized (Sington.class){
                if (uniqueInstance == null){
                    uniqueInstance = new Sington();
                }
            }
        }
        return uniqueInstance;
    }
}


