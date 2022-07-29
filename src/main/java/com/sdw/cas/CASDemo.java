package com.sdw.cas;

import sun.applet.Main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName CASDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 19:38
 * @Version 1.0
 */
public class CASDemo {
    // CAS
    public static void main(String[] args) {
        // 1. 看compareAndSet的源码，里面是使用 == 进行比较的。
        // 2. 由于new的时候声明泛型肯定是装箱类，这个时候传入值类型将会自动装箱
        // 3. 自动装箱的后果就是地址不一致，使用==判断的结果就为false
        // 4. 总结：最好不使用原子类型，使用原子类型得保证比较时候传入的为同一个装箱类
        // integer默认缓存-128->127，超过这个范围就要new对象了，就会分配新的地址，我们看到源码是==，非数值类型，我们比较的是对象的地址
        // 这里比较的是内存地址了，而不是值，所以一直是false
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<Integer>(123, 1);

        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();   // 获得版本号
            System.out.println("a1 ==>" + integerAtomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(integerAtomicStampedReference.compareAndSet(123, 321, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1));

            System.out.println("a2 ==>" + integerAtomicStampedReference.getStamp());

            System.out.println(integerAtomicStampedReference.compareAndSet(321, 123, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1));

            System.out.println("a3 ==>" + integerAtomicStampedReference.getStamp());

        }, "a").start();

        Lock lock = new ReentrantLock(true);

        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();   // 获得版本号
            System.out.println("b1 ==>" + integerAtomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(integerAtomicStampedReference.compareAndSet(123, 66, stamp, stamp + 1));


            System.out.println("b2 ==>" + integerAtomicStampedReference.getStamp());
        }, "b").start();
    }
}
