package com.sda.money;

public class Money {

    private int amount;
    private String currency = "PLN";

    public Money(int amount) {
        this.amount = amount;
    }

    public void addMoney(Money other) {
        amount += other.amount;
    }

    public void substractMoney(Money other) {
        amount -= other.amount;
    }

    public String toString() {
        return amount + currency;
    }
}
