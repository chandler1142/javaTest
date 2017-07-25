package com.java8;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.maxBy;

/**
 * Created by chandlerzhao on 2017/7/25.
 */
public class CollectorTest {

    public static void main(String[] args) {

        Comparator<TestFilter.Person> byAge = Comparator.comparing(TestFilter.Person::getAge).reversed();
        List<TestFilter.Person> list = Stream.of(new TestFilter.Person("tony", 15), new TestFilter.Person("Tom", 12)).collect(Collectors.toList());

        Collections.sort(list, byAge);

        System.out.println(123);

        Function<TestFilter.Person, Integer> function = person -> person.getAge();

        list.stream().collect(maxBy(Comparator.comparing(function)));
    }
}
