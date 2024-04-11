package com.pyhita.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kante_yang
 * @Date: 2024/4/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;
    private String code;
    private int length;
}
