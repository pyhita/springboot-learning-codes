package com.pyhita.selftest.ioc.lifecycle.postprocessor;

import com.pyhita.selftest.ioc.lifecycle.beans.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author pyhita
 * @Date 2024/6/12
 * @Description
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        if (beanClass.equals(Dog.class)) {
//
//        }
        // 可以创建新的Bean，替换掉下面的步骤，代理对象再次创建
        if (Dog.class.equals(beanClass)) {
            System.out.println("3. MyInstantiationAwareBeanPostProcessor # postProcessBeforeInstantiation");
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // 返回false 不会执行 属性注入的操作
        if (bean instanceof Dog) {
            System.out.println("4. MyInstantiationAwareBeanPostProcessor # postProcessAfterInstantiation");
        }
        return true;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (bean instanceof Dog) {
            System.out.println("5. MyInstantiationAwareBeanPostProcessor # postProcessProperties");
        }

        return pvs;
    }
}
