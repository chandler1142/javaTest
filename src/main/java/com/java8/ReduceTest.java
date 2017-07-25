package com.java8;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class ReduceTest {

    public static void main(String[] args) {
        int count = Stream.of(1,2,3)
                .reduce(3, (acc, element) -> acc + element);

        System.out.println(count);

        BinaryOperator<Integer> multiplyOp = (acc, element) -> acc * element;
        Optional<Integer> result = Stream.of(2,3,4).reduce(multiplyOp);
        System.out.println(result.get());



    }
}
