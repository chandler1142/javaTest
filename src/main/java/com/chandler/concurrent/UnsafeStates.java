package com.chandler.concurrent;

/**
 * Created by i325622 on 12/9/16.
 */
public class UnsafeStates {

    private String[] states = new String[]{
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }

    private Long id = 0L;

    public Long getId() {
        return id;
    }

    public static void main(String[] args) {

        UnsafeStates unsafeStates = new UnsafeStates();
        String[] test = unsafeStates.getStates();
        test[0] = "saf";

        Long testId = unsafeStates.getId();
        testId = 1234123L;

        System.out.println(unsafeStates.getStates()[0]);
        System.out.println(unsafeStates.getId());
    }
}
