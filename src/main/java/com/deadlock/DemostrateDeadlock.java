package com.deadlock;

import java.util.Random;

/**
 * Created by i325622 on 6/6/17.
 */
public class DemostrateDeadlock {

    static final int NUM_THREADS = 100;
    static final int NUM_ACCOUNTS = 5;
    static final int NUM_ITERATIONS = 1000000;

    private static final Object tieLock = new Object();

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            Account account = new Account();
            account.setName("Account["+i+"]");
            accounts[i] = account;
        }

        class TransferThread extends Thread {

            public void run() {
                for (int i = 0; i < NUM_THREADS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rnd.nextInt(1000));
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }

        System.out.println("done!");
    }

    public static void transferMoney(Account fromAcct, Account toAcct, DollarAmount amount) {
        System.out.println(Thread.currentThread().getName() + " is running");
        synchronized (fromAcct) {
            synchronized (toAcct) {
                if (fromAcct.getBalance().compareTo(amount.getAmount()) < 0) {
                    throw new RuntimeException("fromAcct [" + fromAcct.getBalance() + "] cannt affort the toAcct + [" + toAcct.getBalance() + "]");
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                    System.out.println(Thread.currentThread().getName() + " is running. From: " + fromAcct.getName() + " To: " + toAcct.getName());
                }
            }
        }
    }

    public static void transferMoneySafe(Account fromAcct, Account toAcct, DollarAmount amount) {
        System.out.println(Thread.currentThread().getName() + " is running");

        class Helper {
            public void transfer() {
                if (fromAcct.getBalance().compareTo(amount.getAmount()) < 0) {
                    throw new RuntimeException("fromAcct [" + fromAcct.getBalance() + "] cannt affort the toAcct + [" + toAcct.getBalance() + "]");
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                    System.out.println(Thread.currentThread().getName() + " is running. From: " + fromAcct.getName() + " To: " + toAcct.getName());
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if(fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if(fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (toAcct) {
                    synchronized (fromAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }
}
