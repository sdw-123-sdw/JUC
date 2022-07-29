package com.sdw.lock8;

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 10:31
 * @Version 1.0
 *
 * 8锁就是关于锁的8个问题
 * 1. 标准情况下，两个线程先打印 发短信 还是 打电话？  1、发短信  2、打电话
 * 1. sendSms延时4秒，两个线程先打印 发短信 还是 打电话？  1、发短信  2、打电话
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        // 锁的存在
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    /**
     * synchronized 锁的是对象方法的调用者
     * 两个方法用的是同一个锁，谁先拿到，谁先执行（只有一个厕所）
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
}
