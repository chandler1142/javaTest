package com.concurrent.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by i325622 on 6/8/17.
 */
public class BoundedBuffer<E> {

    private final Semaphore availableItems, availableSpaces;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    public E get() throws InterruptedException {
        availableItems.acquire();
        E x = doExtract();
        availableSpaces.release();
        return x;
    }

    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E x = items[i];
        takePosition = (++i == items.length)?0:i;
        return x;
    }

    public static void main(String[] args) {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = boundedBuffer.get();
                    System.out.println("Fail!");
                } catch (InterruptedException e) {
                    System.out.println("interrupted exception!");
                    e.printStackTrace();
                }
            }
        };

        taker.start();
        try {
            Thread.sleep(1000);
            taker.interrupt();
            taker.join(1000);
            System.out.println(taker.isAlive());
        } catch (InterruptedException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
}
