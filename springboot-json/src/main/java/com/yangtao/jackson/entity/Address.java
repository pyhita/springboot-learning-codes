package com.yangtao.jackson.entity;

import lombok.Data;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 09:45
 */
@Data
public class Address {
    private String street;
    private String city;
    private int zipcode;

    @Override
    public String toString(){
        return getStreet() + ", "+getCity()+", "+getZipcode();
    }
}
