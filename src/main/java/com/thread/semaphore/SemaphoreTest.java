package com.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by i325622 on 3/22/17.
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorServic
        final Semaphore semaphore = new Semaphore(5);
        for (int i=0;i<10;i++) {
            final int NO = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();

                        System.out.println("Accessing: " + NO);
                        Thread.currentThread().sleep((long) (Math.random() * 1000));
                        semaphore.release();
                        System.out.println("-----------------"+semaphore.availablePermits());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }

        executorService.shutdown();
    }
}
