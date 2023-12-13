package com.yangtao.autoconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kante_yang
 * @Date: 2023/12/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomClient {

    private CustomClientProperties properties;

    public String sendRequest() {
        return String.format("msg from %s, user is %s", properties.getUrl(), properties.getUsername());
    }

}
