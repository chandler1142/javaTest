package com.chandler.finaltest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by i325622 on 12/12/16.
 */
public final class FinalTest {

    static int count = 0;

    public static void main(String[] args) {
//        FinalTest t = new FinalTestalTest();
//        FinalTest.count = 3;
//        System.out.println(t.count);
//        final byte[] test1 = {'a'};
//        byte[] test2 = test1.clone();
//        System.out.println(test1 == test2);
        final String key = "key";
        final String value = "value";
        final Map<String, String> maptest1 = new HashMap<>();
        maptest1.put(key, value);

        final Map<String, String> maptest2 = new HashMap<>();

        System.out.println(maptest1 == maptest2);
    }
}
