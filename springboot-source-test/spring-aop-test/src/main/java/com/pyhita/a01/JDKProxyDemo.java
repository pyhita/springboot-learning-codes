package com.pyhita.a01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyDemo {

    interface Foo {
        void foo();
    }

    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("target foo ... ");
        }
    }

    public static void main(String[] args) {
        // 1 创建目标对象
        Target target = new Target();

        // 2 创建InvocatinHandler
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before invoke .... ");
                Object ret = method.invoke(target, args);
                System.out.println("after invoke .... ");
                return ret;
            }
        };

        // 3 创建代理对象
        Foo proxy = (Foo) Proxy.newProxyInstance(JDKProxyDemo.class.getClassLoader(), target.getClass().getInterfaces(), handler);
        proxy.foo();
    }
}
