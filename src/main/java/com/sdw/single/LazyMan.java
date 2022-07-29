package com.sdw.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName LazyMan
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 16:31
 * @Version 1.0
 */
// 懒汉式
public class LazyMan {

    private static boolean suodewen = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
//            if(lazyMan != null) {
//                throw new RuntimeException("不要企图使用反射来破坏单例模式！！！！！");
//            }
            if(suodewen) {
                suodewen = true;
            } else {
                throw new RuntimeException("不要企图使用反射来破坏单例模式！！！！！");
            }
        }
    }

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if(lazyMan == null) {
            synchronized (LazyMan.class) {
                if(lazyMan == null) {
                    lazyMan = new LazyMan();   //  不是一个原子性操作
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3. 把这个对象指向这个空间
                     *
                     * 期望执行顺序123
                     * 但是实际执行顺序可能为132
                     * 此时 lazyMan ！= null 但是还没有完成构造
                     * new 只是把引用加上了，但是堆那边还没创建完，return就会有问题
                     *
                     * 这里双重检测加锁是保证了操作原子性，只有一个线程能创建一个实例，其他线程无法创建第二个
                     * volatile关键字是为了防止因为指令重排导致的多线程问题，有可能线程A创建一个实例，
                     * 虚拟机只执行了分配空间，对象地址引用这两步，这是线程B过来发现对象已经被创建了，但是获取到的对象是还没有被初始化的
                     * 一开始所有通过第一个if的线程中只有一个线程拿到锁去创建单例，另外的线程因为锁和队列机制强制等待拿锁，一旦单例对象被创建以后其它的线程不用再走第一个if下面的方法而免去了等待拿锁的阻塞
                     */
                }
            }
        }

        return lazyMan;
    }

    // 单线程模式下确实OK
    // 多线程并发
    // 暴力反射!!!
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//         LazyMan instance = LazyMan.getInstance();

        // 反射创建
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan lazyMan = declaredConstructor.newInstance();
        LazyMan instance = declaredConstructor.newInstance();  // 因为这两个对象都不是被instance指向的，所以instance仍为null

        System.out.println(instance);
        System.out.println(lazyMan);
    }
}
