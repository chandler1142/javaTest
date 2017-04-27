package com.thread.futuretask;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by i325622 on 3/23/17.
 */
public class FutureTaskTest {

    public static void main(String[] args) {
//        Thread thread = new Thread(new FutureTask<Object>(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        }));


        while (true) {
            System.out.println(123);
//            return ;
        }
    }
}
