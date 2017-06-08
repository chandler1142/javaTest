package com.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Created by i325622 on 6/3/17.
 */
public class MyThreadFactory implements ThreadFactory {

    private String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyThread(r, poolName);
    }

}
