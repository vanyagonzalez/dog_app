package com.piv.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Ivan_Pavlyukovets on 11/23/2016.
 */
public class CGLibProxy {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Foo proxy = (Foo) Enhancer.create(Foo.class, new MyInvocationHandler(foo));
        proxy.doSomething();
    }

    static class MyInvocationHandler implements MethodInterceptor {

        private Object invocationTarget;

        public MyInvocationHandler(Object invocationTarget) {
            this.invocationTarget = invocationTarget;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("before invoke");
            method.invoke(invocationTarget, args);
            System.out.println("after invoke");
            return null;
        }
    }
}
