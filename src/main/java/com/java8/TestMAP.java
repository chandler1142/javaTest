package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i325622 on 6/27/17.
 */
public class TestMAP {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4,5);
        List<Integer> list3 = Arrays.asList(5,6,7);

        List<List<Integer>> list = Arrays.asList(list1, list2, list3);

    }
}
