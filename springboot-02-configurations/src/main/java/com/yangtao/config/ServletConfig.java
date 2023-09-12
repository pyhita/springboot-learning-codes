package com.yangtao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@ConfigurationProperties("servers")
@Data
// 开启对当前Bean的属性注入校验
@Validated
public class ServletConfig {

    private String ipAddress;
    @Max(value = 33, message = "最大值不能超过33")
    private String port;
    @DurationUnit(ChronoUnit.HOURS)
    private Duration timeout;
//    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
