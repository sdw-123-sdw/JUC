package com.sdw.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName Test
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 20:15
 * @Version 1.0
 */

/**
 * 题目要求：一行代码实现
 * 现在有6个用户进行筛选
 * 1. ID 必须是偶数
 * 2. 年龄必须大于23岁
 * 3. 用户名转换为大写字母
 * 4. 洪湖名字母倒着排序
 * 5. 只输出一个用户！
 */
public class Test {
    public static void main(String[] args) {
        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(5, "e", 25);
        User user6 = new User(6, "f", 26);
        // 集合就只是用来存储
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        // 计算交给stream流
        // lambda表达式、链式编程、函数式接口、stream流式计算
        users.stream()
                .filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 23)
                .map(user -> {
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .sorted((u1, u2) -> u2.getName().compareTo(u1.getName()))    // 排序，默认升序
                .limit(1)    // 分页
                .forEach(System.out::println);
    }
}
