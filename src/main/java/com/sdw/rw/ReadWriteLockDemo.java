package com.sdw.rw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/15 18:44
 * @Version 1.0
 *
 * 独占锁（写锁），一次只能被一个线程占有
 * 共享锁（读锁），一次可以被多个线程占有
 * ReadWriteLock
 * 读-读  可以共存
 * 读-写  不可共存
 * 写-写  不可共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "value");
            }, String.valueOf(i)).start();
        }

        // 读取
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {
    // volatile，共享资源同步,volatile只能保证可见性不能保证原子性的
    // volatile两个作用，1.保池可见性，2.防止指令重排
    // Volatie只能保证可见性, 原理就是强制从主存去读取这个变量, 而不是从方法栈中去读取 , 保证了线程之间的可见性, 但是在并发操作时是可以被任何一个线程修改的, 所以只有可见性没有原子性
    // volatile关键字仅仅能保证变量写操作的原子性，不保证复合操作，比如说读写操作的原子性
    private volatile Map<String, Object> map = new HashMap<>();

    // 存, 写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入成功");
    }

    // 取，读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取成功" + o);
    }
}

/**
 * 加锁版
 */
class MyCacheLock {
    // volatile，共享资源同步,volatile只能保证可见性不能保证原子性的
    // volatile两个作用，1.保池可见性，2.防止指令重排
    // Volatie只能保证可见性, 原理就是强制从主存去读取这个变量, 而不是从方法栈中去读取 , 保证了线程之间的可见性, 但是在并发操作时是可以被任何一个线程修改的, 所以只有可见性没有原子性
    // volatile关键字仅仅能保证变量写操作的原子性，不保证复合操作，比如说读写操作的原子性
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁：更加细粒度的控制
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    // 存, 写入的时候，只希望同时只有一个线程写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 取，读的时候，所有人都可以读
    public void get(String key) {
        lock.readLock().lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}