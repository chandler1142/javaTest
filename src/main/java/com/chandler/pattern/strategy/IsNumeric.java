package com.chandler.pattern.strategy;

/**
 * Created by i325622 on 11/28/16.
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
