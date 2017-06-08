package com.concurrent;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by i325622 on 3/20/17.
 */
public class TestThread {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService);

        Long result = 0L;

        Long start = System.currentTimeMillis();
        for(long i=0;i<500000000l;i++) {
            result += i;
        }

        Long end = System.currentTimeMillis();

//        System.out.println(result);
        System.out.println(end - start);

        Long start1 = System.currentTimeMillis();

        Task task1 = new Task(1L, 50000000L);
        Task task11 = new Task(50000001L, 100000000L);

        Task task2 = new Task(100000001L, 150000000L);
        Task task22 = new Task(150000001L, 200000000L);

        Task task3 = new Task(200000001L, 250000000L);
        Task task33 = new Task(250000001L, 300000000L);

        Task task4 = new Task(300000001L, 350000000L);
        Task task44 = new Task(350000001L, 400000000L);

        Task task5 = new Task(400000001L, 450000000L);
        Task task55 = new Task(450000001L, 500000000L);

        Future<Long> r1 = completionService.submit(task1);
        Future<Long> r2 = completionService.submit(task2);
        Future<Long> r3 = completionService.submit(task3);
        Future<Long> r4 = completionService.submit(task4);
        Future<Long> r5 = completionService.submit(task5);

        Future<Long> r11 = completionService.submit(task11);
        Future<Long> r22 = completionService.submit(task22);
        Future<Long> r33 = completionService.submit(task33);
        Future<Long> r44 = completionService.submit(task44);
        Future<Long> r55 = completionService.submit(task55);
        Long rr = 0l;

        for(int j=0;j<10;j++) {
            Future<Long> f = completionService.take();
            rr += f.get();
        }
        System.out.println(rr);
        Long end1 = System.currentTimeMillis();
        executorService.shutdown();
        System.out.println(end1 - start1);

    }

    static class Task implements Callable<Long> {
        private Long from;
        private Long target;

        public Task(Long from, Long target) {
            this.from = from;
            this.target = target;
        }

        @Override
        public Long call() throws Exception {
            Long result = 0L;
            for(Long i=from;i<target;i++) {
                result += i;
            }
            return result;
        }
    }
}
