package com.dynamicProxy;

import com.chandler.pattern.proxy.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by i325622 on 6/19/17.
 */
public class ProxyTest {

    public static void main(String[] args) {
        Class[] classes = new Class[]{Business1.class, Business2.class};

        LogInvocationHandler logInvocationHandler = new LogInvocationHandler(new Business());

        ClassLoader classLoader = DynamicProxy.class.getClassLoader();

        Business2 business = (Business2) Proxy.newProxyInstance(classLoader, classes, logInvocationHandler);
        business.doSth2();
        ((Business1)business).doSth1();

    }
}
