package com.pyhita.a01;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo {

    static class Target {
        public void foo() {
            System.out.println("target foo ... ");
        }
    }

    public static void main(String[] args) {
        Target target = new Target();

        // 2 创建代理对象

        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before invoke .... ");
                Object ret = method.invoke(target, args);
                proxy.invoke(target, args);//  没有用反射
                proxy.invokeSuper(obj, args); // 没有用反射
                System.out.println("after invoke");
                return ret;
            }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(methodInterceptor);
        enhancer.setSuperclass(target.getClass());
        enhancer.setClassLoader(CglibProxyDemo.class.getClassLoader());

        Target proxy = (Target) enhancer.create();
        proxy.foo();
    }
}
