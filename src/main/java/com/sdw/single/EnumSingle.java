package com.sdw.single;

import sun.applet.Main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName EnumSingle
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 18:43
 * @Version 1.0
 */
// enum 是一个什么？ 本身也是一个Class类
// jvm保证枚举是唯一
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        /**
         * 编译的class中有无参构造
         * 但是实际上这个无参构造并不存在
         * java.lang.NoSuchMethodException: com.sdw.single.EnumSingle.<init>()
         */
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
