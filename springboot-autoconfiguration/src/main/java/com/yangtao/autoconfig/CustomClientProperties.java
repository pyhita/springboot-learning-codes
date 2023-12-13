package com.yangtao.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2023/12/13
 */
@ConfigurationProperties(prefix = "spring.custom.client")
@Data
public class CustomClientProperties {

    private String url = "locallhost";
    private String username = "root";
    private String password = "root";

}
