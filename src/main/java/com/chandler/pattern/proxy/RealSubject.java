package com.chandler.pattern.proxy;

/**
 * Created by i325622 on 12/7/16.
 */
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }

    @Override
    public String testReturn() {
        System.out.println("Test return");
        return "This is return value";
    }
}
