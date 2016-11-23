package com.piv.proxy;

/**
 * Created by Ivan_Pavlyukovets on 11/23/2016.
 */
public class Foo implements IFoo {
    @Override
    public void doSomething() {
        System.out.println("in Foo.doSomething");
    }
}
