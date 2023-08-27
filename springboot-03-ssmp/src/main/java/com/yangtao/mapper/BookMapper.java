package com.yangtao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangtao.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    @Select("SELECT * FROM tbl_book WHERE id = #{id}")
    Book getById(Integer id);


}
