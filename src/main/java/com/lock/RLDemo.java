package com.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by i325622 on 6/21/17.
 */
public class RLDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final ReentrantLock lock = new ReentrantLock();

        class Worker implements Runnable {

            private final String name;

            Worker(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                lock.lock();
                try {
                    if(lock.isHeldByCurrentThread()) {
                        System.out.printf("Thread %s entered critical seciton. %n", name);
                        System.out.printf("Thread %s performing work. %n", name);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.printf("Thread %s finished working.%n", name);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        executorService.execute(new Worker("ThdA"));
        executorService.execute(new Worker("ThdB"));

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(123132);
        executorService.shutdown();
    }
}
