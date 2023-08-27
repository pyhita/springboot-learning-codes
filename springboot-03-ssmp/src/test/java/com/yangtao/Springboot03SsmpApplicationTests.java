package com.yangtao;

import com.yangtao.entity.Book;
import com.yangtao.mapper.BookMapper;
import com.yangtao.mapper.DemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot03SsmpApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private BookMapper bookMapper;
    // 测试整合mybatis
    @Test
    void testMybatis() {
        Book book = bookMapper.getById(1);
        System.out.println(book);
    }

}
