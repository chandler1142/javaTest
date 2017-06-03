package com.concurrent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * Created by i325622 on 5/29/17.
 */
public class FutureTaskTest {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1, 0l, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

    private Map<String, FutureTask<String>> tasks = new HashMap<String, FutureTask<String>>();

    public void addTaskList(List<Callable<String>> tasksList) {
        Runnable runnable = () ->{
            System.out.println(12312);
        };

        int rel = 3;
        Thread t = new Thread(() -> {
            System.out.println(rel);
        });


    }


    public static void main(String[] args) {
        Predicate<Integer> atLeast5 = x -> x > 5;;

        boolean result = atLeast5.test(3);
        System.out.println(result);
    }

}
