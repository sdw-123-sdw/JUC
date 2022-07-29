package com.sdw.lock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestSpinLock
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/18 15:54
 * @Version 1.0
 */
public class TestSpinLock {
    public static void main(String[] args) {
        // 底层使用的自旋锁CAS
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(() -> {
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        }, "T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        }, "T2").start();
    }
}
