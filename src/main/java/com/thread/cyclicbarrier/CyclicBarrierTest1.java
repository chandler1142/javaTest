package com.thread.cyclicbarrier;

import java.util.SimpleTimeZone;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by i325622 on 3/22/17.
 */
public class CyclicBarrierTest1 {

    private static final Integer SIZE = 5;

    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(SIZE);
        for(int i=0;i<SIZE;i++) {
            new Thread(new Inner()).start();
        }
    }

    static class Inner implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " continued.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
