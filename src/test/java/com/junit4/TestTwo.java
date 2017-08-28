package com.junit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

/**
 * Created by chandlerzhao on 2017/8/16.
 */
@FixMethodOrder(MethodSorters.JVM)
public class TestTwo {

    @Test
    public void testA() {
        System.out.println("this is testA two");
    }

    @Test
    public void testB() {
        System.out.println("this is testB two");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
        System.out.println("testing exception");
        new ArrayList<Object>().get(0);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


}
