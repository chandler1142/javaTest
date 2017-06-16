package com.concurrent.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by i325622 on 6/11/17.
 */
public class TestThreadFactory {

    private static final AtomicInteger created = new AtomicInteger();
    private static final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    public static Thread newThread(Runnable r) {
        created.incrementAndGet();
        return threadFactory.newThread(r);
    }

    public void testPoolExtension() throws InterruptedException {
        int MAX_SIZE = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIZE);

        for (int i = 0; i < 2 * MAX_SIZE; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 20 && created.get() < MAX_SIZE; i++) {
//            Thread.sleep(1000);
            TestThreadFactory.newThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println(created.get());
        System.out.println(MAX_SIZE);
        System.out.println(created.get() == MAX_SIZE);
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadFactory testThreadFactory = new TestThreadFactory();
        testThreadFactory.testPoolExtension();
    }

}
