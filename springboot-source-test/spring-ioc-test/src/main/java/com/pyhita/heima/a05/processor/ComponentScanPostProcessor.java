package com.pyhita.heima.a05.processor;

import com.pyhita.heima.a05.Config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.stereotype.Component;

public class ComponentScanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (componentScan != null) {
                // 获取注解 相关的信息
                for (String p : componentScan.basePackages()) {
                    // com.pyhita.a05.component -> classpath*:com/pyhita/a05/component/**/*.class
                    String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
                    System.out.println("path = " + path);
                    // 扫描到所有的类
                    CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
                    AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);

                    if (registry instanceof DefaultListableBeanFactory ctx) {

                    }

                    for (Resource resource : resources) {
                        System.out.println("resource = " + resource);
                        // 判断是否加了Component注解
                        boolean hasAnnotation = readerFactory.getMetadataReader(resource).getAnnotationMetadata().hasAnnotation(Component.class.getName());
                        // 判断是否添加了派生注解
                        boolean hasMetaAnnotation = readerFactory.getMetadataReader(resource).getAnnotationMetadata().hasMetaAnnotation(Component.class.getName());
                        System.out.println(hasAnnotation + " " + hasMetaAnnotation);

                        if (hasAnnotation || hasMetaAnnotation) {
                            // 注册 BeanDefinition
                            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(readerFactory.getMetadataReader(resource).getAnnotationMetadata().getClassName()).getBeanDefinition();
                            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
                            registry.registerBeanDefinition(beanName, beanDefinition);
                        }
                    }
                }

            }

        } catch (Exception e) {

        }

    }
}
