package com.piv.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Ivan_Pavlyukovets on 11/23/2016.
 */
public class JdkProxy {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        IFoo foo = new Foo();

        IFoo proxy = (IFoo)
                Proxy.newProxyInstance(IFoo.class.getClassLoader(),
                        foo.getClass().getInterfaces(),
                        new MyInvocationHandler(foo));

        proxy.doSomething();
    }

    public static class MyInvocationHandler implements InvocationHandler {
        private Object invocationTarget;

        public MyInvocationHandler(Object invocationTarget) {
            this.invocationTarget = invocationTarget;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before invoke");
            method.invoke(invocationTarget, args);
            System.out.println("after invoke");
            return null;
        }
    }
}
