package com.pyhita.selftest.ioc.lifecycle.postprocessor;

import com.pyhita.selftest.ioc.lifecycle.beans.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author pyhita
 * @Date 2024/6/12
 * @Description
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Dog) {
            System.out.println("8. MyBeanPostProcessor # postProcessBeforeInitialization");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Dog) {
            System.out.println("MyBeanPostProcessor # postProcessAfterInitialization");
        }
        return bean;
    }
}
