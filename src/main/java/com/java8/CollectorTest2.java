package com.java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by chandlerzhao on 2017/7/25.
 */
public class CollectorTest2 {

    public static void main(String[] args) {
        Student student1 = new Student(1, "tom", false, 15);
        Student student2 = new Student(2, "chandler", true, 16);
        Student student3 = new Student(3, "yummy", false, 17);
        Student student4 = new Student(4, "tony", true, 16);

        List<Student> studentList = Stream
                .of(student1, student2, student3, student4)
                .collect(Collectors.toList());

        Map<Boolean, List<Student>> map1 = studentList.stream().collect(partitioningBy(Student::getGender));

        Map<Integer, List<Student>> map2 = studentList.stream().collect(groupingBy(student -> student.getAge()));


        System.out.println(student1 instanceof Object);


        StringBuilder builder = new StringBuilder("[");
        for (Student student : studentList) {
            if (builder.length() > 1) {
                builder.append(", ");
            }
            String name = student.getName();
            builder.append(name);
        }
        builder.append("]");
        String result = builder.toString();
        System.out.println(result);

        List<Student> studentList2 = Stream
                .of(student1)
                .collect(Collectors.toList());

        studentList2.stream().collect(Collectors.reducing(null, (index, student) -> {
            System.out.println(index + "   " + student.toString());
            if (student.getAge() == 17)
                return student;
            else
                return null;
        }));

        StringBuilder reduced = studentList.stream()
                .map(Student::getName)
                .reduce(new StringBuilder(), (bb, name) -> {
                            if (bb.length() > 1)
                                bb.append(", ");
                            bb.append(name);
                            return bb;
                        }, (left, right) -> {
                            System.out.println("left:" + left);
                            System.out.println("right:" + right);
                            return left.append(right);
                        }
                );

//        StringJoiner stringJoiner = studentList.stream().map(Student::getName).reduce(new StringJoiner(", ", "[", "]"), (str, name) -> {
//
//            StringJoiner sj = new StringJoiner(str);
//            sj.add(name);
//            return sj.toString();
//        });

        StringBuilder sb1 = new StringBuilder("chandler");
        StringBuilder sb2 = new StringBuilder("1142");

        sb1.append(sb2, 1, sb2.length());
        System.out.println(sb1.toString());

        Map<Student, Integer> countOfStudent = new HashMap<>();
    }
}
