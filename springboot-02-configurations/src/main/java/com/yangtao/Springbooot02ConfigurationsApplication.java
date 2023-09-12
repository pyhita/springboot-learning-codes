package com.yangtao;

import com.alibaba.druid.pool.DruidDataSource;
import com.yangtao.config.ServletConfig;
import com.yangtao.config.c_configurationproperties.Cat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({Cat.class})
public class Springbooot02ConfigurationsApplication {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Springbooot02ConfigurationsApplication.class, args);
        ServletConfig bean = ctx.getBean(ServletConfig.class);
        DruidDataSource data = ctx.getBean(DruidDataSource.class);
        System.out.println(data.getDriverClassName());
        System.out.println(bean);
    }
}
