package com.sdw.testvolatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VDemo02
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 11:02
 * @Version 1.0
 */
// 不保证原子性
public class VDemo02 {

    // volatile 不保证原子性
    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger(0);
    // private volatile static int num = 0;

    public static void add() {
        // num++;  // 非原子性操作
        num.getAndIncrement();   // 执行+1操作  CAS
    }

    // 理论上num结果应该为2W
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }, String.valueOf(i)).start();
        }

        if(Thread.activeCount() > 2) {
            Thread.yield();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "----->" + num);
    }
}
