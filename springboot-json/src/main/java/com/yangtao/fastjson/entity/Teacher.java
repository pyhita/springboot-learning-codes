package com.yangtao.fastjson.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: kante_yang
 * @Date: 2023/9/22 09:45
 */
@Data
public class Teacher {

    private String teacherName;
    private Integer teacherAge;
    private Course course;
    private List<Student> students;
}
