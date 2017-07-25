package com.chandler.pattern.TaskChain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by i325622 on 11/28/16.
 */
public class TestMain {

    public static void main(String[] args) {
//        ProcessingObject<String> processor1 = new HeaderTextProcessing();
//        ProcessingObject<String> processor2 = new SpellCheckProcessing();
//
//        processor1.setSuccessor(processor2);
//
//        String result = processor1.handle("Are't labdas really sexy?");
//        System.out.println(result);

        UnaryOperator<String> headerProcessing = (String text) -> "From Jack, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String result = pipeline.apply("Are't labdas really sexy?");
        System.out.println(result);
    }
}
