package com.concurrent;

import java.util.concurrent.*;

/**
 * Created by i325622 on 6/3/17.
 */
public class ExecutorTest {


    public static class ThreadTest extends Thread {

        public ThreadTest(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + this.getName());
        }
    }

    public static void main(String[] args) {

    }

}
