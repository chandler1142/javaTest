package com.deadlock;

/**
 * Created by i325622 on 6/6/17.
 */
public class Account {

    private String name;

    DollarAmount dollarAmount = new DollarAmount(10000);

    public void debit(DollarAmount amount) {
        dollarAmount.setAmount(dollarAmount.getAmount() - amount.getAmount());
    }

    public void credit(DollarAmount amount) {
        dollarAmount.setAmount(dollarAmount.getAmount() + amount.getAmount());
    }

    public Integer getBalance() {
        return dollarAmount.getAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
