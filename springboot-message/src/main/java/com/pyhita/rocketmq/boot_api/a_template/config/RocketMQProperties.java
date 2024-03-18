package com.pyhita.rocketmq.boot_api.a_template.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQProperties {

    private String nameServer;
    private Producer producer = new Producer();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Producer {
        private String group;
    }

}
