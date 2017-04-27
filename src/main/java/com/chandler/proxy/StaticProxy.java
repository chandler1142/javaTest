package com.chandler.proxy;

/**
 * Created by i325622 on 12/6/16.
 */
public class StaticProxy implements HelloWorld {

    private HelloWorld helloWorld;

    public StaticProxy(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @Override
    public void print() {
        System.out.println("Before Hello World!");
        helloWorld.print();
        System.out.println("After Hello World!");
    }

}
