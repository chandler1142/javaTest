package com.chandler.annotation;

import java.lang.annotation.*;

/**
 * Created by i325622 on 12/12/16.
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface FruitColor {

    public enum Color {
        BLUE, GREEN, RED
    }

    Color fruitColor() default Color.BLUE;

}
