package com.yangtao.jackson2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 14:57
 */
@Data
@AllArgsConstructor
public class Transaction {

    private String type = null;
    private Date date = null;

}
