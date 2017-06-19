package com.spring.aop;

import org.springframework.stereotype.Service;

/**
 * Created by i325622 on 6/19/17.
 */
public class UserServiceImpl implements IUserService {

    @Override
    public void addUser(User user) {
        System.out.println("add success");
    }

    @Override
    public void updateUser(User user) {
        System.out.println("update success");
    }
}
