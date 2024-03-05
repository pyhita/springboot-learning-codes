package com.pyhita.a03;

import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

public class Proxy extends Target {

    private MethodInterceptor methodInterceptor;

    public Proxy(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    private static Method save1;
    private static Method save2;
    private static Method save3;

    static {
        try {
            save1 = Target.class.getMethod("save");
            save2 = Target.class.getMethod("save", int.class);
            save3 = Target.class.getMethod("save", long.class);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, save1, new Object[0], null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{i}, null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this, save3, new Object[]{j}, null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
