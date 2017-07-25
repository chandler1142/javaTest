package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.*;

/**
 * Created by chandlerzhao on 2017/7/25.
 */
public class ManualDiceRolls {

    private static final int N = 1000000000;

    private final double fraction;
    private final Map<Integer, Double> results;
    private final int numberOfThreads;
    private final ExecutorService executorService;
    private final int workPerThread;

    public static void main(String[] args) {
        ManualDiceRolls rolls = new ManualDiceRolls();
        System.out.println("average: " + (double) 1 / 6);
        rolls.simulateDiceRolls();
    }

    public ManualDiceRolls() {
        this.fraction = 1.0 / N;
        results = new ConcurrentHashMap<>();
        numberOfThreads = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(numberOfThreads);
        workPerThread = N / numberOfThreads;
    }

    public void simulateDiceRolls() {
        List<Future<?>> futureList = submitJobs();
        awaitCompletion(futureList);
        printResults();
    }

    private void printResults() {
        results.entrySet().forEach(System.out::println);
        OptionalDouble sum = results.entrySet().stream().mapToDouble(n -> n.getValue()).reduce((s, i) -> {
            return s += i;
        });
        System.out.println("sum: " + sum.getAsDouble());
    }

    private List<Future<?>> submitJobs() {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(executorService.submit(makeJob()));
        }
        return futures;
    }

    private Runnable makeJob() {
        return () -> {
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows(threadLocalRandom);
                accumulateResult(entry);
            }
        };
    }

    private void accumulateResult(int entry) {
        results.compute(entry, (key, previous) ->
                previous == null ? fraction : (previous + fraction)
        );
    }

    private int twoDiceThrows(ThreadLocalRandom threadLocalRandom) {
        int first = threadLocalRandom.nextInt(1, 7);
        int second = threadLocalRandom.nextInt(1, 7);
        return first + second;
    }

    private void awaitCompletion(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
