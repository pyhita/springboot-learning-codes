package com.yangtao.jackson2.apitest;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 14:56
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangtao.jackson2.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * jackson 对于日期的转化
 */
public class ObjectMapperDateTransfer {

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    // Date --> Long
    @Test
    public void test1() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        String transactionString = objectMapper.writeValueAsString(transaction);
        System.out.println(transactionString);
    }

    // Date --> String
    @Test
    public void test2() throws Exception {
        Transaction transaction = new Transaction("transfer", new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(format);

        String jsonString = objectMapper.writeValueAsString(transaction);
        System.out.println(jsonString);
    }

}
