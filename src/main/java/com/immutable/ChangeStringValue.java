package com.immutable;

import java.lang.reflect.Field;

/**
 * Created by i325622 on 3/22/17.
 */
public class ChangeStringValue {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        String test = "hello world";
        System.out.println(test);

        Field valueOfString = String.class.getDeclaredField("value");
        valueOfString.setAccessible(true);

        char[] c = (char[])valueOfString.get(test);
        c[5] = '_';
        System.out.println(test);
    }
}
