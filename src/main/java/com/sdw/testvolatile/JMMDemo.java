package com.sdw.testvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMMDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 10:26
 * @Version 1.0
 */
public class JMMDemo {
    // 不加 volatile 程序就会死循环
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) {


        new Thread(() -> {
            while (num == 0) {  // 线程1 对主内存的变化不知道的

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
