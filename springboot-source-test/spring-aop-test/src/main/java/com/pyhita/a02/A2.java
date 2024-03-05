package com.pyhita.a02;

public class A2 {

    interface Foo {
        void foo();

        void bar();
    }

//    interface InvocationHandler {
//        Object invoke(Object proxy, Method method, Object[] args);
//    }

    static class Target implements Foo {

        @Override
        public void foo() {
            System.out.println("foo .... ");
        }

        @Override
        public void bar() {
            System.out.println("bar .... ");
        }
    }

    public static void main(String[] args) {

        final Foo target = new Target();

        Foo foo = new $Proxy0((proxy, method, params) -> {
            try {
                System.out.println("before ... ");
                Object ret = method.invoke(target, params);
                return ret;
            } catch (Exception e) {

            }
            return null;
        });

        foo.foo();
        foo.bar();

    }

}
