package com.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Created by i325622 on 6/3/17.
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
