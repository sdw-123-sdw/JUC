package com.sdw.function;

import java.util.function.Function;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 17:49
 * @Version 1.0
 */

/**
 * Function  函数型接口，有一个输入参数，有一个输出参数
 * 只要是函数型接口 都可以用 lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
        // 变成一个工具类
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        Function<String, String> function = str -> str;
        System.out.println(function.apply("12334"));
    }
}
