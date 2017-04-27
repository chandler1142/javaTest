package com.chandler.pattern.template;

import java.util.function.Consumer;

/**
 * Created by i325622 on 11/28/16.
 */
public abstract class OnlineBanking {

    public void processCustomer(int id) {

        Customer c = Database.getCustomerById(id);
        makeCustomerHappy(c);
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerById(id);
        makeCustomerHappy.accept(c);
    }


    abstract void makeCustomerHappy(Customer c);
}
