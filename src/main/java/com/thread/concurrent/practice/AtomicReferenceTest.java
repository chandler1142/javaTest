package com.thread.concurrent.practice;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by i325622 on 4/13/17.
 */
public class AtomicReferenceTest {

    public final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    public BigInteger[] service(BigInteger i) {
        if(i.equals(lastNumber)) {
            return lastFactors.get();
        } else {
            BigInteger[] factors = {i.add(new BigInteger("1")), i.add(new BigInteger("2"))};
            lastNumber.set(i);
            lastFactors.set(factors);
            return factors;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReferenceTest test = new AtomicReferenceTest();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                BigInteger[] bigIntegers = test.service(new BigInteger("9"));
                System.out.println(Thread.currentThread().getName()+ ":" + test.lastNumber.toString() + "  " + bigIntegers[0].toString() + "  " + bigIntegers[1].toString());
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                BigInteger[] bigIntegers = test.service(new BigInteger("100"));
                System.out.println(Thread.currentThread().getName()+ ":" + test.lastNumber.toString() + "   " + bigIntegers[0].toString() + "  " + bigIntegers[1].toString());
            }
        };

        Thread thread1 = new Thread(runnable1, "thread1");
        Thread thread2 = new Thread(runnable2, "thread2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
