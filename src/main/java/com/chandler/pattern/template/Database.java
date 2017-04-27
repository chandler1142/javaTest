package com.chandler.pattern.template;

/**
 * Created by i325622 on 11/28/16.
 */
public class Database {

    public static Customer getCustomerById(int id) {

        Customer customer = new Customer();

        switch (id) {
            case 1:
                customer.setId(1);
                customer.setName("Jack");
                break;
            case 2:
                customer.setId(2);
                customer.setName("John");
                break;
            default:
                customer.setId(1);
                customer.setName("Jack");
        }
        return customer;
    }
}
