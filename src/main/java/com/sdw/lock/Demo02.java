package com.sdw.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Demo02
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/18 15:23
 * @Version 1.0
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

// 可重入锁也就是某个线程已经获得某个锁，可以再次获取锁而不会出现死锁
// 就是某一个线程已经获得了锁，还可以再次获得锁而不会出现死锁。也就是两个加锁的方法，可以相互调用。
class Phone2 {

    Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();   // 细解问题：lock.lock();    lock.unlock();
        // lock 锁必须配对，否则就会死在里面
        try {
            System.out.println(Thread.currentThread().getName() + "发短信");
            call();   // 这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "打电话");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}