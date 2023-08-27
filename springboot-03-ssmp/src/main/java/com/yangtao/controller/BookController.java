package com.yangtao.controller;

import com.yangtao.entity.Book;
import com.yangtao.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable("id") Integer id) {

//        return bookMapper.getById(id);
        return bookMapper.selectById(id);
    }


}
