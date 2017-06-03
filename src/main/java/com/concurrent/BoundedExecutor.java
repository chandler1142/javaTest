package com.concurrent;

import javax.swing.plaf.SeparatorUI;
import java.util.concurrent.*;

/**
 * Created by i325622 on 6/3/17.
 */
public class BoundedExecutor {

    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        this.semaphore.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            System.out.println("RejectedExecutionException occurs");
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1));
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        BoundedExecutor boundedExecutor = new BoundedExecutor(threadPoolExecutor, new Semaphore(1));

        Task task1 = new Task("task1");
        Task task2 = new Task("task2");

        boundedExecutor.submitTask(task1);
        boundedExecutor.submitTask(task2);
    }

    static class Task extends Thread {

        public Task(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName());
        }
    }
}
