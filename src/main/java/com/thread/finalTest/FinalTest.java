package com.thread.finalTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i325622 on 4/13/17.
 */
public class FinalTest {

    public static final List<Long> testList = new ArrayList<>();

    public static final Long testLong = 0L;

    public void increment() {
        testList.add(new Long(1));
    }


    public static void main(String[] args) throws InterruptedException {

        FinalTest finalTest = new FinalTest();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                finalTest.increment();
            }
        };

        Thread thread1 = new Thread(runnable, "thread1");
        Thread thread2 = new Thread(runnable, "thread2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(testList.size());
    }

}
