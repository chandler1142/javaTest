package com.thread.notifyandwait.busywaiting;

import java.util.concurrent.TimeUnit;

/**
 * Created by i325622 on 3/22/17.
 */
public class BusyWaiting implements Runnable {
    private Monitor monitor;

    public BusyWaiting(Monitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("i'm going.");
            monitor.gotMesasge();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Monitor implements Runnable {

        private volatile boolean go = false;

        public synchronized void gotMesasge() {
            go = true;
            notify();
        }

        public synchronized void watching() throws InterruptedException {
            while ( go == false) {
                System.out.println("waiting");
                wait();
            }
            System.out.println("He has gone!");
        }

        public void run() {
            try {
                watching();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        BusyWaiting o = new BusyWaiting(monitor);
        new Thread(o).start();
        new Thread(monitor).start();
    }
}
