package com.yangtao.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kante_yang
 * @Date: 2023/12/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement // 可以写出为xml文档
public class Person {

    private String name;
    private Integer age;
    private Date birthday;

}
