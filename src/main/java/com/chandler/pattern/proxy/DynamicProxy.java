package com.chandler.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by i325622 on 12/7/16.
 */
public class DynamicProxy implements InvocationHandler{

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before rent house");

        System.out.println("Method: " + method);

        Object obj = method.invoke(subject, args);
        if(obj == null)
            return null;
        System.out.println(obj.toString());
        System.out.println("after rent house");

        return null;
    }

}
