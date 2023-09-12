package com.yangtao.config.d_propertysource;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Data
@Configuration
@PropertySources(
        @PropertySource(value = "classpath:jdbc.properties")
)
public class PropertySourceTest {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;

    @Test
    void test() {
        System.out.println(url);
        System.out.println(username);
    }
}
