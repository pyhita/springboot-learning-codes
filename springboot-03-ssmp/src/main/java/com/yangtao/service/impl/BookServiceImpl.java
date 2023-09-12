package com.yangtao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtao.entity.Book;
import com.yangtao.mapper.BookMapper;
import com.yangtao.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Boolean save(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public Boolean update(Book book) {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book queryBook) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(queryBook.getName()), Book::getName, queryBook.getName());
        lqw.like(Strings.isNotEmpty(queryBook.getType()), Book::getType, queryBook.getType());
        lqw.like(Strings.isNotEmpty(queryBook.getDescription()), Book::getDescription, queryBook.getDescription());
        IPage<Book> page = new Page<>(currentPage, pageSize);
        bookMapper.selectPage(page, lqw);

        return page;
    }
}