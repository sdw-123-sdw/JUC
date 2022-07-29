package com.sdw.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 16:51
 * @Version 1.0
 *
 * 3.增加了一个普通方法hello， 是先发短信还是先hello？  先hello
 * 4.两个对象，两个同步方法，发短信 还是 打电话？
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

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

class Phone2 {
    /**
     * synchronized 锁的对象是方法的调用者
     */
    public synchronized void sendSms() {
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }

    /**
     * 这里没有锁,不是同步方法,不受锁的限制
     */
    public void hello() {
        System.out.println("hello");
    }
}
