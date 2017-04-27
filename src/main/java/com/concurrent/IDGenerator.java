package com.concurrent;

/**
 * Created by i325622 on 3/20/17.
 */
public class IDGenerator {

    long lastId;

    public long incrementLastId() {
        return ++lastId;
    }


    public static void main(String[] args) {
        IDGenerator idGenerator = new IDGenerator();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1:" + idGenerator.incrementLastId());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2:" + idGenerator.incrementLastId());
            }
        });

        int i=0;
        thread1.start();
        thread2.start();

        i++;

    }
}
