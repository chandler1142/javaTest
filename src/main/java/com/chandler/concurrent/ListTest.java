package com.chandler.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by i325622 on 12/8/16.
 */
public class ListTest {

    public static void main(String[] args) {


        Long start = System.currentTimeMillis();
        Vector vector = new Vector();
        for (int i = 0; i < 1000000; i++) {
            vector.add(i);
        }
        Long end = System.currentTimeMillis();

        System.out.println(end-start);

    }
}
