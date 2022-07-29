package com.sdw.function;

import java.util.function.Predicate;

/**
 * @ClassName Demo02
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 17:57
 * @Version 1.0
 */
// 断定型接口：有一个输入参数，返回值只能是 布尔值！
public class Demo02 {
    public static void main(String[] args) {
        // 可以判断字符串是否为空
        Predicate<String> stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        };

        System.out.println(stringPredicate.test("1231"));
        System.out.println(stringPredicate.test(""));
    }
}
