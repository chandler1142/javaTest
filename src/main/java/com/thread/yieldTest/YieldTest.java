package com.thread.yieldTest;

/**
 * Created by i325622 on 3/22/17.
 */
public class YieldTest implements Runnable{

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        if(Thread.currentThread().getName().equals("thread1"))
            Thread.yield();
    }

    public static void main(String[] args) {
//        YieldTest test = new YieldTest();
//        Thread thread1 = new Thread(test, "thread1");
//        Thread thread2 = new Thread(test, "thread2");
//        thread1.start();
//        thread2.start();

        int[] array = {1,1,2,3,2};
        int result = array[0];
        for(int i=1;i<array.length;i++) {
            result = result ^ array[i];
        }
        System.out.println(result);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
