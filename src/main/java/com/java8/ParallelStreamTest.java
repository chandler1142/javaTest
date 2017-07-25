package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by chandlerzhao on 2017/7/25.
 */
public class ParallelStreamTest {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println((int)(Math.random()*10));
            Student s = new Student(i, "111", true, (int)(Math.random()*100));
            list.add(s);
        }

//        Long start = System.currentTimeMillis();
//        OptionalDouble averageAge1 = list.stream().mapToInt(Student::getAge).average();
//        System.out.println(averageAge1.getAsDouble());
//        Long end = System.currentTimeMillis();
//        System.out.println(end-start);
//
//        start = System.currentTimeMillis();
//        OptionalDouble averageAge2 = list.parallelStream().mapToInt(Student::getAge).average();
//        System.out.println(averageAge2.getAsDouble());
//        end = System.currentTimeMillis();
//        System.out.println(end-start);



    }
}
