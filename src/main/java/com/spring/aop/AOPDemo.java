package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by i325622 on 6/19/17.
 */
public class AOPDemo {

    //前置通知
    public void startTransaction() {
        System.out.println("begin transaction ");
    }

    //后置通知
    public void commitTransaction() {
        System.out.println("commit transaction ");
    }

    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("begin transaction");

        joinPoint.proceed();

        System.out.println("commit transaction");
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationcontext.xml");
        IUserService userService = (IUserService) applicationContext.getBean("userService");
        userService.addUser(new User());
        userService.updateUser(new User());
    }

}
