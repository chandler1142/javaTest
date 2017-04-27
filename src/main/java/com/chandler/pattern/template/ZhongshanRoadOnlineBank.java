package com.chandler.pattern.template;

/**
 * Created by i325622 on 11/28/16.
 */
public class ZhongshanRoadOnlineBank extends OnlineBanking {

    public static void main(String[] args) {
//        ZhongshanRoadOnlineBank zhongshanRoadOnlineBank = new ZhongshanRoadOnlineBank();
//        zhongshanRoadOnlineBank.processCustomer(1);
        new ZhongshanRoadOnlineBank().processCustomer(1, (customer -> {
            System.out.println("make " + customer.getName() + " happy");
        }));
    }

    @Override
    void makeCustomerHappy(Customer c) {

    }
}
