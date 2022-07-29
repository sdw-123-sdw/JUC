package com.sdw.lock;

/**
 * @ClassName SpinlockDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/18 15:46
 * @Version 1.0
 */

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinlockDemo {
    // CAS
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    // 当传入线程是空自旋等不空
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "=====> myLock");

        // 自旋锁
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "=====> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }
}
