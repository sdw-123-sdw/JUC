package com.sdw.function;

import java.util.function.Consumer;

/**
 * @ClassName Demo03
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 18:05
 * @Version 1.0
 */
// Consumer  消费型解耦：只有输入，没有返回值
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> stringConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        stringConsumer.accept("12312");
    }
}
