package com.sdw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test4
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 17:28
 * @Version 1.0
 *
 * 7。一个静态同步方法，一个普通同步方法，一个对象， 先打电话还是发短信？
 * 8.一个静态同步方法，一个普通同步方法，两个对象， 先打电话还是发短信？
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象的， 但是class类模板只有一个 static，锁的是class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        // 锁的存在
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

/**
 * Phone3 只有唯一的一个Class对象
 */
class Phone4 {
    /**
     * 静态的同步方法 锁的是class类模板
     */
    public static synchronized void sendSms() {
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    // 普通的同步方法 锁的是调用者，两个不是同一个锁
    public synchronized void call() {
        System.out.println("打电话");
    }
}