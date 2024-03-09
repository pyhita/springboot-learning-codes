package com.pyhita.a03;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class A3 {

    public static void main(String[] args) {

        Target target = new Target();

        Target proxy = new Proxy(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before ... ");
//                method.invoke(target, args); 反射调用
                proxy.invoke(target, args); // 无反射
                proxy.invokeSuper(obj, args); // 无反射
                return null;
            }
        });

        proxy.save();
        proxy.save(111);
        proxy.save(111L);
    }
}
