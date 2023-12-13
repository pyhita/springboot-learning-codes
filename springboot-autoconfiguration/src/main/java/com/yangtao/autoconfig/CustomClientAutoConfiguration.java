package com.yangtao.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2023/12/13
 */
@Configuration
@EnableConfigurationProperties(CustomClientProperties.class)
@ConditionalOnClass(SqlSessionFactoryBean.class)
@Slf4j
public class CustomClientAutoConfiguration {

    @Autowired
    private CustomClientProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public CustomClient customClient() {
        CustomClient customClient = new CustomClient(properties);
        log.info("customer client initalize successfully.");
        return customClient;
    }

}
