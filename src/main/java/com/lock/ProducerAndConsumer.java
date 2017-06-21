package com.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by i325622 on 6/21/17.
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {

        Shared s = new Shared();
        new Producer(s.getLock(), s).start();
        new Consumer(s.getLock(), s).start();
    }

    static class Shared {
        private char c;

        private volatile boolean available;

        private final Lock lock;

        private final Condition condition;

        Shared() {
            available = false;
            lock = new ReentrantLock();
            condition = lock.newCondition();
        }

        Lock getLock() {
            return this.lock;
        }

        char getSharedChar() {
            lock.lock();
            try {

                while (!available) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                available = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
            return c;
        }

        void setSharedChar(char c) {
            lock.lock();
            try {
                while (available) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.c = c;
                available = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

    static class Producer extends Thread {

        private final Lock l;

        private final Shared s;

        public Producer(Lock l, Shared s) {
            this.l = l;
            this.s = s;
        }

        public void run() {
            for (char a = 'A'; a <= 'Z'; a++) {
                l.lock();
                s.setSharedChar(a);
                System.out.println(a + " produced by producer.");
                l.unlock();
            }
        }
    }

    static class Consumer extends Thread {

        private final Lock l;

        private final Shared s;

        public Consumer(Lock l, Shared s) {
            this.l = l;
            this.s = s;
        }

        public void run() {
            char c;
            do {
                l.lock();
                c = s.getSharedChar();
                System.out.println(c + " consumed by consumer.");
                l.unlock();
            } while ( c != 'Z');
        }
    }

}
