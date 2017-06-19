package com.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by i325622 on 6/19/17.
 */
public class LogInvocationHandler implements InvocationHandler {

    private Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object rev = method.invoke(target, args);
        if(method.getName().equals("doSth2")) {
            System.out.println("Record Log");
        }
        return rev;
    }
}
