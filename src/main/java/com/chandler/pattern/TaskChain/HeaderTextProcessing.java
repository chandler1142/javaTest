package com.chandler.pattern.TaskChain;

/**
 * Created by i325622 on 11/28/16.
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From Jack, Mario and Alan: "  + input;
    }
}
