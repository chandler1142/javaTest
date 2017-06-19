package com.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i325622 on 6/16/17.
 */
public class TestFilter {


    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Tom1", 13));
        list.add(new Person("Tom2", 14));
        list.add(new Person("Tom3", 15));
        list.add(new Person("Tom4", 16));




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
