package com.yangtao.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yangtao.entity.Book;
import com.yangtao.response.R;
import com.yangtao.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public R getAll() {

        return new R(true, bookService.getAll(), "操作成功");
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {

//        return bookMapper.getById(id);
        return new R(true, bookService.getById(id), "操作成功");
    }

    @PostMapping
    public R save(@RequestBody Book book) {
        Boolean flag = bookService.save(book);
        // 模拟发生异常
//        if (true) throw new IllegalArgumentException("参数不正确");
        System.out.println("hot deploy");
        return new R(flag, flag == true ? "操作成功" : "操作失败");
    }

    @PutMapping
    public R update(@RequestBody Book book) {
        Boolean flag = bookService.update(book);
        return new R(flag, flag ? "操作成功！" : "操作失败！");
    }


    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Integer id) {
        Boolean flag = bookService.delete(id);
        return new R(flag, flag ? "操作成功！" : "操作失败！");
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable("currentPage") Integer current,
                               @PathVariable("pageSize") Integer size,
                     Book book) {
        System.out.println(book);
        IPage<Book> page = bookService.getPage(current, size, book);
        if (current > page.getPages()) {
            page = bookService.getPage((int) page.getPages(), size, book);
        }

        return new R(true, page, "操作成功");
    }

}
