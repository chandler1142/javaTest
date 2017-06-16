package com.concurrent.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by i325622 on 6/8/17.
 */
public class PutTaskTest {

    private static final ExecutorService pool = Executors.newFixedThreadPool(1);
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nParis;
    private final BarrierTimer timer;

    PutTaskTest(int capacity, int nParis, int nTrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = nTrials;
        this.nParis = nParis;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(nParis * 2 + 1, timer);
    }

    public static void main(String[] args) {
        new PutTaskTest(10, 10, 10).testPerformance();
        pool.shutdown();
    }

    void test() {
        for (int i = 0; i < nParis; i++) {
            pool.execute(new Producer());
            pool.execute(new Consumer());
        }
        try {
            barrier.await();
            barrier.await();
            System.out.println(putSum.toString().endsWith(takeSum.toString()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    void testPerformance() {
        timer.clear();
        for (int i = 0; i < nParis; i++) {
            pool.execute(new Producer());
            pool.execute(new Consumer());
        }
        try {
            barrier.await();
            barrier.await();
            long nsPerTime = timer.getTime() / (nParis * (long) nTrials);
            System.out.println("Throughput: " + nsPerTime + " ns/item");
            System.out.println(putSum.get() == takeSum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    class BarrierTimer implements Runnable {
        private boolean started;
        private long startTime, endTime;

        @Override
        public synchronized void run() {
            if (!started) {
                started = true;
                startTime = System.nanoTime();
            } else {
                endTime = System.nanoTime();
            }
        }

        public synchronized void clear() {
            started = false;
        }

        public synchronized long getTime() {
            return endTime - startTime;
        }

    }

    class Producer implements Runnable {
        @Override
        public void run() {
            int seed = (this.hashCode() ^ (int) System.nanoTime());
            int sum = 0;
            try {
                System.out.println("Producer: " + Thread.currentThread().getName() + " is ready to work");
                barrier.await();
                for (int i = nTrials; i > 0; i--) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; i--) {
                    sum += bb.get();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }


}
