package com.thread.ProduceAndConsumer;

import java.util.PriorityQueue;

/**
 * Created by i325622 on 3/22/17.
 */
public class ProAndCon {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) {
        ProAndCon cap = new ProAndCon();
        Consumer cus = cap.new Consumer();
        Producer pro = cap.new Producer();
        Thread cusT = new Thread(cus);
        Thread proT = new Thread(pro);

        proT.start();
        cusT.start();
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println(Thread.currentThread().getState());
                            System.out.println("队列已满，等待空余的空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                        System.out.println("生产者醒过来");
                    }

                    queue.offer(1);   // 每次插入一个元素
                    queue.notify();
                    Thread.currentThread().yield();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                }
            }
        }

    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据。。。");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                        System.out.println("消费者醒过来");
                    }

                    queue.poll();
                    Thread.currentThread().yield();
                    queue.notify();
                    System.out.println("从队列中取走一个元素，队列中剩余" + queue.size() + "个");
                }
            }
        }
    }
}
