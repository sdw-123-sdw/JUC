package com.sdw.single;

/**
 * @ClassName Holder
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 18:21
 * @Version 1.0
 */
// 静态内部类
public class Holder {
    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }
}
