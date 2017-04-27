package com.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by i325622 on 3/22/17.
 */
public class CountdownLatchTest {

    private static CountDownLatch countDownLatch;
    private static final Integer SIZE = 2;

//    public static void main(String[] args) throws InterruptedException {
//
//        countDownLatch = new CountDownLatch(SIZE);
//
//        Thread thread1 = new Thread(new Runner(), "thread1");
//        Thread thread2 = new Thread(new Runner(), "thread2");
//        Thread thread3 = new Thread(new Runner(), "thread3");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//
//        countDownLatch.await();
//
//
//        System.out.println("main thread run~~~");
//    }
//
//    static class Runner implements Runnable {
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName() + " run!!");
//            countDownLatch.countDown();
//        }
//    }

    public static void main(String[] args) {
        countDownLatch = new CountDownLatch(2);
        ThreadTest test = new ThreadTest(countDownLatch, "thread1");
        test.start();
    }

    static class ThreadTest extends Thread {

        private CountDownLatch countDownLatch;

        public ThreadTest(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.setName(name);
        }

        @Override
        public void run() {
            try {
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("thread2 running...");
                        countDownLatch.countDown();
                    }
                }, "thread2");

                Thread thread3 = new Thread(new Runnable() {
                    @Override

                    public void run() {
                        System.out.println("thread3 running...");
                        countDownLatch.countDown();
                    }
                }, "thread3");

                thread2.start();
                thread3.start();
                countDownLatch.await();

                System.out.println("thread1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
