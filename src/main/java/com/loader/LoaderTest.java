package com.loader;

import javax.sql.rowset.JdbcRowSet;

/**
 * Created by i325622 on 3/21/17.
 */
public class LoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(classLoader);
//        classLoader.loadClass("com.loader.Test");
//        classLoader.loadClass("com.loader.Test");
//
//        Class testClass = Class.forName("com.loader.Test");
//        Test test = (Test) testClass.newInstance();
//        System.out.println(test);
//        System.out.println(String.class.getClassLoader());
//        System.out.println(Integer.class.getClassLoader());
//        System.out.println(JdbcRowSet.class.getClassLoader());

//        Class testClass = Class.forName("com.loader.Test");
//        System.out.println(testClass);
        Test test = new Test();
        Class<Test> testClass = (Class<Test>) test.getClass();
        System.out.println(testClass.getName());

        Integer num = new Integer(123);
        num = 1234;
    }




}
