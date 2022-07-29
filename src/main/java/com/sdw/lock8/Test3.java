package com.sdw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test3
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 17:08
 * @Version 1.0
 *
 * 5.增加两个静态的同步方法，只有一个对象，先打印 发短信还是打电话？  发短信  打电话
 * 6.两个对象！两个静态的同步方法，只有一个对象，先打印 发短信还是打电话？ 发短信  打电话
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象的， 但是class类模板只有一个 static，锁的是class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

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
class Phone3 {
    /**
     * synchronized 锁的对象是方法的调用者
     * static 静态方法
     * 类一加载就有了！是一个class, 锁的是class
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
    public static synchronized void call() {
        System.out.println("打电话");
    }
}