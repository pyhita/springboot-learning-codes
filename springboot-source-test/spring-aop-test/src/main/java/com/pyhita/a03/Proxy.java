package com.pyhita.a03;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Proxy extends Target {

    private MethodInterceptor methodInterceptor;

    public Proxy(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    private static Method save1;
    private static Method save2;
    private static Method save3;
    private static MethodProxy saveSuper1;
    private static MethodProxy saveSuper2;
    private static MethodProxy saveSuper3;

    static {
        try {
            save1 = Target.class.getMethod("save");
            save2 = Target.class.getMethod("save", int.class);
            save3 = Target.class.getMethod("save", long.class);
            saveSuper1 = MethodProxy.create(Target.class, Proxy.class, "()V", "save", "saveSuper");
            saveSuper2 = MethodProxy.create(Target.class, Proxy.class, "(I)V", "save", "saveSuper");
            saveSuper3 = MethodProxy.create(Target.class, Proxy.class, "(J)V", "save", "saveSuper");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }


    public void saveSuper() {
        super.save();
    }
    public void saveSuper(int i) {
        super.save(i);
    }
    public void saveSuper(long j) {
        super.save(j);
    }

    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, save1, new Object[0], saveSuper1);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{i}, saveSuper2);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this, save3, new Object[]{j}, saveSuper3);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
