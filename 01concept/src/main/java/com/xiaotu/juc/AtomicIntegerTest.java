package com.xiaotu.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private AtomicInteger count = new AtomicInteger();

    public void increment(){
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }

}
