package com.pyhita.heima.a05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class Bean2 {

    private static final Logger log = LoggerFactory.getLogger(Bean2.class);

    public Bean2() {
        log.debug("我被 Spring 管理啦");
    }
}