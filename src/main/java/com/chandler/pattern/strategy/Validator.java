package com.chandler.pattern.strategy;

/**
 * Created by i325622 on 11/28/16.
 */
public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

    public static void main(String[] args) {

//        Validator validator1 = new Validator(new IsNumeric());
//        Validator validator2 = new Validator(new IsAllLowerCase());
//
//        boolean b1 = validator2.validate("aaaaa");
//        boolean b2 = validator1.validate("123123");
//
//        System.out.println(b1 + "  " + b2);

        Validator numericValidator = new Validator((String s) -> s.matches("\\d+]"));
        Validator lowerValidator = new Validator((String s) -> s.matches("[a-z]+"));

        System.out.println(numericValidator.validate("bbb"));
        System.out.println(lowerValidator.validate("adaf"));

    }

}
