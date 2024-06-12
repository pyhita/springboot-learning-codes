package com.pyhita.heima.a02;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBeanFactory {

//    public static void main(String[] args) {
//
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        AbstractBeanDefinition configDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).getBeanDefinition();
//        beanFactory.registerBeanDefinition("config", configDefinition);
//        // 拿到工厂中有的BeanName
//        // 没有Bean1 和 Bean2 说明此时Configuration坠儿 和@Bean并没有被解析
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//
//        // 向容器中注册后置处理器，并在此调用，可以看到一些后置处理器已经添加了
//        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//
//        // 调用添加的后置处理器
//        Map<String, BeanFactoryPostProcessor> processors = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
//        for (BeanFactoryPostProcessor factoryPostProcessor : processors.values()) {
//            factoryPostProcessor.postProcessBeanFactory(beanFactory);
//        }
//
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//
//        // BeanPostProcessor 生命周期的阶段 对Bean 进行扩展
////        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
//        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream().sorted(beanFactory.getDependencyComparator()).forEach(beanFactory::addBeanPostProcessor);
//        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
//
//        // 优先级排序，默认情况下，Autowired先执行
//        System.out.println(beanFactory.getBean(Bean1.class).getInter());
//    }

//    @Configuration
//    static class Config {
//        @Bean
//        public Bean1 bean1() {
//            return new Bean1();
//        }
//
//        @Bean
//        public Bean2 bean2() {
//            return new Bean2();
//        }
//
//        @Bean
//        public Bean3 bean3() {
//            return new Bean3();
//        }
//
//        @Bean
//        public Bean4 bean4() {
//            return new Bean4();
//        }
//    }

    interface Inter {

    }

    static class Bean3 implements Inter {

    }

    static class Bean4 implements Inter {

    }

    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("构造 Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        @Autowired
        @Resource(name = "bean4")
        private Inter bean3;

        public Inter getInter() {
            return bean3;
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }
}
