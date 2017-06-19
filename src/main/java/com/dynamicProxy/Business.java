package com.dynamicProxy;

/**
 * Created by i325622 on 6/19/17.
 */
public class Business implements Business1, Business2 {
    @Override
    public void doSth1() {
        System.out.println("do sth 1");
    }

    @Override
    public void doSth2() {
        System.out.println("do sth 2");
    }
}
