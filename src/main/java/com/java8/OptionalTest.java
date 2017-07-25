package com.java8;

import java.util.Optional;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional emptyOptional = Optional.empty();
        Optional nullOptional = Optional.ofNullable(null);

        System.out.println(emptyOptional.orElseGet(() -> {
            return new Integer(123);
        }));
        System.out.println(nullOptional.orElse(new Integer(2)));


    }
}
