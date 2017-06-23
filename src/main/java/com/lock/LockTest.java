package com.lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

/**
 * Created by i325622 on 6/22/17.
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ReentrantLock l = new ReentrantLock();

        LockThread2 lt2 = new LockThread2(l);
        lt2.start();


        LockThread lt1 = new LockThread(l);
        lt1.start();



        Thread.currentThread().sleep(1000);
        lt1.interrupt();
        System.out.println();
    }

    public static class LockThread extends Thread {

        ReentrantLock l;

        public LockThread(ReentrantLock l) {
            this.l = l;
        }

        @Override
        public void run() {
            try {
                l.lockInterruptibly();
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("thread is running");
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } finally {

                System.out.println("release");
                if(l.isHeldByCurrentThread()) {
                    l.unlock();
                }
            }
        }

    }

    public static class LockThread2 extends Thread {

        ReentrantLock l;

        public LockThread2(ReentrantLock l) {
            this.l = l;
        }

        @Override
        public void run() {
            try {
                l.lock();
                System.out.println("thread2 acquired the lock");
                for(;;);
            } catch (Exception e) {
                System.out.println("exception");
            } finally {
                System.out.println("release");
                l.unlock();
            }
        }

    }


}
