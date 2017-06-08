package com.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by i325622 on 6/5/17.
 */
public class InterruptExample1 {

    private static final ScheduledExecutorService cancelService = Executors.newScheduledThreadPool(3);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelService.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }


    public static void main(String[] args) {
        Caller caller = new Caller();
        try {
            caller.callTimeRun(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread is running");
                }
            }, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Caller {
        public void callTimeRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
            cancelService.schedule(r, 1, TimeUnit.SECONDS);
        }
    }

}
