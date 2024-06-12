package com.yangtao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author pyhita
 * @Date 2024/4/26
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqUserLogin {

    private String username;
    private Integer age;
    private String phone;

}
