package com.chandler.annotation;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by i325622 on 12/12/16.
 */
public class Apple {

//    @FruitColor(fruitColor = FruitColor.Color.BLUE)
//    String fruitColor;
//
//    public static void main(String[] args) {
//        Apple apple = new Apple();
//        System.out.println(apple.fruitColor);
//    }

    public class Person {
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(1);
//        list.add(1);
//
//        List<Integer> newList = list.stream().distinct().map(id  -> id*12).collect(Collectors.toList());
//        for (Integer i : newList) {
//            System.out.println(i);
//        }
//        List<Long> list = new ArrayList<>();
//        list.add(1L);
//        list.add(2L);
//        list.add(3L);
//        list.add(4L);
//
//        list.remove(3L);
//        list.forEach(l -> {
//            System.out.println(l);
//        });
//        Calendar cal = Calendar.getInstance();
//        System.out.println(cal.get(Calendar.MONTH));
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        calendar.roll(Calendar.DATE, false);
        System.out.println(calendar.get(Calendar.DATE));

        for(int i=0;i<31;i++) {
            System.out.println((i+1)+"");
        }
    }


}
