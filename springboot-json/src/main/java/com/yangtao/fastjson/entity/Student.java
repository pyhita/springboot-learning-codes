package com.yangtao.fastjson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kante_yang
 * @Date: 2023/9/22 09:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String studentName;
    private Integer studentAge;
}
