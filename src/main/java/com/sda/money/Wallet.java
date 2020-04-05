package com.sda.money;

public class Wallet {

    private Money money = new Money(0);

    public void putMoney(Money other) {
        money.addMoney(other);
    }

    public void takeMoney(Money other) {
        money.substractMoney(other);
    }

    public String toString() {
        return "Wallet with:\n" + money.toString();
    }
}
