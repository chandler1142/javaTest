package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by i325622 on 6/3/17.
 */
public class MyThread extends Thread {

    private static final String DEFAULT_NAME = "MyAppThread";
    private static volatile  boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyThread(Runnable r, String name) {
        super(r, name+ "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {

                    }
                }
        );
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
