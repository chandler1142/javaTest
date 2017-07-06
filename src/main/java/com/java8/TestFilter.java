package com.java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by i325622 on 6/16/17.
 */
public class TestFilter {


    public static void main(String[] args) {
        List<Person> list = Stream.of(new Person("Tom1", 13),
                new Person("Tom2", 14),
                new Person("Tom3", 15),
                new Person("Tom4", 16)).collect(Collectors.toList());
        
        long count = list.stream().filter(n -> {
            return n.getAge() > 13;
        }).count();

        System.out.println(count);
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }


    }
}
