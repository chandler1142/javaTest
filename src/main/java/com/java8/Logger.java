package com.java8;

import java.util.function.Supplier;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class Logger {

    public void debug(String msg) {
        System.out.println(msg);
    }

    public void debug(Supplier<TestFilter.Person> msg) {
        //judge idDebugable
        debug(msg.get().getName().toString());
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        TestFilter.Person p = new TestFilter.Person("1",2);
        logger.debug(() -> p);
    }
}
