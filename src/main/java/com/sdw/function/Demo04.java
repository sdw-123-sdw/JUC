package com.sdw.function;

import java.util.function.Supplier;

/**
 * @ClassName Demo04
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 19:47
 * @Version 1.0
 */
// 供给型接口，没有参数，只有返回值
public class Demo04 {
    public static void main(String[] args) {
        Supplier<String> stringSupplier = new Supplier<String>() {
            @Override
            public String get() {
                return "1024";
            }
        };

        System.out.println(stringSupplier.get());
    }
}
