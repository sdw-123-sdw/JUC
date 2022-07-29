package com.sdw.lock;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/18 11:28
 * @Version 1.0
 */
// lock、synchronized都有可重复锁，非公平锁
// synchronized
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "发短信");
        call();   // 这里也有锁
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "打电话");
    }
}
