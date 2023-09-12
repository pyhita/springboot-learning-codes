package com.yangtao.config.c_configurationproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "baby-cat")
public class Cat {
    private String name;
    private String kind;


}
