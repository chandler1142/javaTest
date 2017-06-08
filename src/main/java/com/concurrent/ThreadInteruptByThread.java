package com.concurrent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by i325622 on 6/4/17.
 */
public class ThreadInteruptByThread {

    public static class PrimeGenerator implements Runnable {

        private final List<BigInteger> primes = new ArrayList<>();
        private volatile boolean cancelled;

        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            System.out.println(Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                p = p.nextProbablePrime();
                synchronized (this) {
                    primes.add(p);
                }
            }
        }

        public void cancel() {
            this.cancelled = true;
        }

        public synchronized List<BigInteger> get() {
            return new ArrayList<BigInteger>(primes);
        }
    }

    public static void main(String[] args) {
        PrimeGenerator generator = new PrimeGenerator();
        Thread t = new Thread(generator);
        t.start();
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        List<BigInteger> list = generator.get();
        list.forEach(n-> {
            System.out.println(n.toString());
        });
        System.out.println(123);

        System.out.println(t.isInterrupted());
    }
}
