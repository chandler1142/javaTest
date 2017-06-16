package com.thread.ProduceAndConsumer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by i325622 on 6/13/17.
 */
public class ProducerAndConsumerTest {

    private static final Integer DEFAULT_SIZE = 100;
    private static final Queue<Integer> queue = new ArrayBlockingQueue<Integer>(DEFAULT_SIZE);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

    public static void main(String[] args) {

        Producer p1 = new Producer("1");
        Producer p2 = new Producer("2");

        Consumer c1 = new Consumer("1");
        Consumer c2 = new Consumer("2");
        Consumer c3 = new Consumer("3");
        Consumer c4 = new Consumer("4");
        Consumer c5 = new Consumer("5");

        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class Producer extends Thread {

        public Producer(String name) {
            this.setName("Producer" + name);
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            while (true) {
                synchronized (queue) {
                    while (queue.size() == DEFAULT_SIZE) {
                        try {
                            System.out.println(this.getName() + " 队列已满，等待空余的空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(this.getName() + " 醒过来");

                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println(this.getName() + " 向队列取中插入一个元素，队列剩余空间：" + (DEFAULT_SIZE - queue.size()));
                }
                Thread.currentThread().yield();
            }
        }


    }

    static class Consumer extends Thread {
        public Consumer(String name) {
            this.setName("Consumer" + name);
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println(this.getName() + " 队列空，等待数据。。。");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(this.getName() + "消费者醒过来");

                    }

                    queue.poll();
                    queue.notifyAll();
                    System.out.println(this.getName() + " 从队列中取走一个元素，队列中剩余" + queue.size() + "个");
                }
                Thread.currentThread().yield();
            }
        }
    }

}
