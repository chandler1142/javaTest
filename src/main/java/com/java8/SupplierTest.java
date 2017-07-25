package com.java8;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class SupplierTest {

    public void testSupplier(Supplier<Integer> supplier) {
        Integer integer = supplier.get();
        System.out.println(integer);
    }

    public static void main(String[] args) {

        SupplierTest test = new SupplierTest();
        test.testSupplier(() -> new Integer(3));
    }


}
