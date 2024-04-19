package com.pyhita.lombok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @Author pyhita
 * @Date 2024/4/19
 * @Description
 */
@Data // 生成 getter setter toString equals hashcode
@Accessors(chain = true) // 链式属性赋值
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private Integer age;
    private LocalDate birthday;
    private String phone;

}
