package com.pyhita.heima.a05.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.util.Set;

public class AtBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(new ClassPathResource("com/pyhita/heima/a05/Config.class"));
            // 找到所有被@Bean 标注的方法
            Set<MethodMetadata> methods = metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
            for (MethodMetadata method : methods) {
                System.out.println(method.getMethodName());
                // 生成BeanDefinition
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
                // 设置工厂方法
                builder.setFactoryMethodOnBean(method.getMethodName(), "config");
                // 设置自动装配模式
                builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
                // 设置init 方法
                String m = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
                if (m.length() > 0) {
                    builder.setInitMethodName(m);
                }
                registry.registerBeanDefinition(method.getMethodName(), builder.getBeanDefinition());
            }
        } catch (Exception e) {

        }
    }
}
