package com.java8;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by i325622 on 6/29/17.
 */
public class TestReduce {

    public static void main(String[] args) {
        int count = Stream.of(1, 2, 3).reduce(3, (acc, element) -> {
            return acc + element;
        });
        System.out.println(count);

        BinaryOperator<Integer> binaryOperator = (acc, element) -> acc + element;

        Function<Integer, Integer> acct;
//        acct.apply()


    }
}
