package com.pyhita.heima.a05;

import com.pyhita.heima.a05.processor.AtBeanPostProcessor;
import com.pyhita.heima.a05.processor.ComponentScanPostProcessor;
import com.pyhita.heima.a05.processor.MapperPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

public class A05Application {

    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx = new GenericApplicationContext();
        ctx.registerBean("config", Config.class);
//        ctx.registerBean(ConfigurationClassPostProcessor.class);
        // 注册Mapper 扫描器
//        ctx.registerBean(MapperScannerConfigurer.class, bd -> {
//            bd.getPropertyValues().add("basePackage", "com.pyhita.a05.mapper");
//        });
        // 添加工厂后置处理器
        ctx.registerBean(ComponentScanPostProcessor.class);
        ctx.registerBean(AtBeanPostProcessor.class);
        ctx.registerBean(MapperPostProcessor.class);

        ctx.refresh();
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        ctx.close();
    }
}
