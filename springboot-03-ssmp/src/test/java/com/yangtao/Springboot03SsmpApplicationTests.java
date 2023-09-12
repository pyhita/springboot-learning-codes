package com.yangtao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    // 测试MP 分页功能
    @Test
    void testGetPage() {
        IPage page = new Page(2, 5);
        bookMapper.selectPage(page, null);

        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }

    // 测试MP条件查询功能
    @Test
    void testGetBy2() {
//        QueryWrapper<Book> qw = new QueryWrapper<>();
//        qw.like("name", "Spring");
//        List<Book> books = bookMapper.selectList(qw);
//        System.out.println(books);
        String name = "Spring";
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name != null, Book::getName, name);
        List<Book> books = bookMapper.selectList(lambdaQueryWrapper);
        System.out.println(books);
    }

}
