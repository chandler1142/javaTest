package com.chandler.concurrent;

/**
 * Created by i325622 on 12/7/16.
 */
public class ConcurrentCalculator {

//    public int count = 0;
//
//    public void add() {
//        count++;
//    }
//
//    public static void main(String[] args) {
//
//        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
//        Thread thread1 = new Thread("th1") {
//            @Override
//            public void run() {
//                synchronized (concurrentCalculator) {
//                    concurrentCalculator.add();
//                    System.out.println(super.getName() + ": " + concurrentCalculator.count);
//                }
//            }
//        };
//
//        Thread thread2 = new Thread("th2") {
//            @Override
//            public void run() {
//                synchronized (concurrentCalculator) {
//                    concurrentCalculator.add();
//                    System.out.println(super.getName() + ": " + concurrentCalculator.count);
//                }
//            }
//        };
//
//        thread1.start();
//        thread2.start();
//    }

    public int count = 0;

    public int add() {
        return count++;
    }

    public static void main(String[] args) {
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();

        Thread thread1 = new Thread("th1") {
            public void run() {
                concurrentCalculator.add();

                System.out.println(concurrentCalculator.getCount());
            }
        };

        Thread thread2 = new Thread("th2") {
            public void run() {
                concurrentCalculator.add();

                System.out.println(concurrentCalculator.getCount());
            }
        };

        thread1.start();
        thread2.start();

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
