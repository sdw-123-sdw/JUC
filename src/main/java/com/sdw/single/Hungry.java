package com.sdw.single;

/**
 * @ClassName Hungry
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 16:12
 * @Version 1.0
 */
// 饿汉式单例,构造器私有
// 一上来就将对象加载
public class Hungry {

    // 可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
