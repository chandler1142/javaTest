package com.concurrent;

/**
 * Created by i325622 on 5/30/17.
 */
public class ThreadLocalTest {

    public static Integer shareInt = 1;

    private static ThreadLocal<Integer> threadLocalList = new ThreadLocal<Integer> () {
        @Override
        protected Integer initialValue() {
            return shareInt;
        }
    };

    private String getNextNum() {
        threadLocalList.set(threadLocalList.get() + 1);
        return ""+threadLocalList.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        TestNum test1 = new TestNum(threadLocalTest);
        TestNum test2 = new TestNum(threadLocalTest);
        TestNum test3 = new TestNum(threadLocalTest);

        test1.start();
        test2.start();
        test3.start();
    }


    private static class TestNum extends Thread {

        private ThreadLocalTest test;

        public TestNum(ThreadLocalTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + test.getNextNum() + "]");
            }
        }
    }

}
