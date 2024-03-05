package com.pyhita.a02;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public class $Proxy0 extends Proxy implements A2.Foo {

//    private InvocationHandler h; // 将变化的部分，通过接口抽取出去

    private static Method foo;
    private static Method bar;

    static {
        try {
            foo = A2.Foo.class.getDeclaredMethod("foo");
            bar = A2.Foo.class.getDeclaredMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public void foo() {
        try {
            // 调用的方法不是固定的，可以通过反射来调用
            h.invoke(this, foo, new Object[0]); // 将需要调用的方法 和 参数
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void bar() {
        try {
            // 调用的方法不是固定的，可以通过反射来调用
            h.invoke(this, bar, new Object[0]); // 将需要调用的方法 和 参数
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}
