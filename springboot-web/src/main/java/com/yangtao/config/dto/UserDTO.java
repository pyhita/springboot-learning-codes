package com.yangtao.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author pyhita
 * @Date 2024/4/26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private Integer age;
    private String phone;
    private LocalDate birthday;

}
