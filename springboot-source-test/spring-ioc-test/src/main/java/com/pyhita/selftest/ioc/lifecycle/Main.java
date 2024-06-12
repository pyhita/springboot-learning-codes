package com.pyhita.selftest.ioc.lifecycle;

import com.pyhita.selftest.ioc.lifecycle.config.ConfigClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author pyhita
 * @Date 2024/6/12
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConfigClass.class);
        ctx.refresh();
        ctx.close();
    }
}
