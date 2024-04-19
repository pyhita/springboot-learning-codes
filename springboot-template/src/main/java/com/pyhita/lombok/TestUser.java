package com.pyhita.lombok;

import com.pyhita.lombok.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */
@Slf4j // 快速给类中定义 日志变量
public class TestUser {

    public static void main(String[] args) {
        testLog();
    }

    private static void testChain() {
        User user = new User();
        user.setAge(18).setName("kante").setBirthday(LocalDate.now()).setPhone("110");

        System.out.println("user = " + user);
    }

    private static void testLog() {
        log.info("info");
        log.debug("debug");
        log.error("error");
    }
}
