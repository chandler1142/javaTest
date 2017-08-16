package jvm.gc;

import java.lang.ref.PhantomReference;

/**
 * Created by chandlerzhao on 2017/7/29.
 */
public class TestOne {

    private static Object obj = null;


    public static void main(String[] args) {
        obj = new Object();

        System.gc();
    }
}
