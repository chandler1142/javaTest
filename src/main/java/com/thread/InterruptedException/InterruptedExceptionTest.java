package com.thread.InterruptedException;

/**
 * Created by i325622 on 3/23/17.
 */
public class InterruptedExceptionTest {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runner());
        thread.start();

//        thread.interrupt();
        while(true){
            if(thread.getState().toString().equals("TERMINATED")) {
                System.out.println(thread.getState().toString());
                break;
            }
        }
//        try {
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        thread.interrupt();
    }

    static class Runner implements java.lang.Runnable{
        @Override
        public void run() {
//            try {
//                while(true) {
//                    Thread.currentThread().sleep(1000);
//                }
                System.out.println(123);
//            } catch (InterruptedException e) {
//                System.out.println("线程被打断");
//            }
        }
    }
}
