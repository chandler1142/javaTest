package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by i325622 on 6/12/17.
 */
public class StreamIterator {


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        OptionalInt i = Arrays.stream(arr).filter(a -> {
            return a > 2;
        }).findFirst();

        System.out.println(i.getAsInt());


        List<String> strs = Stream.of("a", "b").collect(Collectors.toList());

        List<String> list = asList("a", "b", "helo").stream().map(r -> r.toUpperCase()).collect(Collectors.toList());
        list.stream().forEach(n-> System.out.println(n));
    }
}
