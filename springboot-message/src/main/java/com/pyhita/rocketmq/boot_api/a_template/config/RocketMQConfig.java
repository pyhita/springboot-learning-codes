package com.pyhita.rocketmq.boot_api.a_template.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQConfig {


//    @Autowired
//    private RocketMQProperties properties;
//
//    @Bean
//    public RocketMQTemplate rocketMQTemplate() {
//        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
//        rocketMQTemplate.setProducer(defaultMQProducer());
//        return rocketMQTemplate;
//    }
//
//    @Bean
//    public DefaultMQProducer defaultMQProducer() {
//        DefaultMQProducer producer = new DefaultMQProducer();
//        producer.setNamesrvAddr(properties.getNameServer());
//        producer.setProducerGroup(properties.getProducer().getGroup());
//
//        return producer;
//    }

}
