package com.sda.money;

import com.sda.money.exceptions.NotEnoughMoneyException;

public class Person {

    private String name;
    private Wallet wallet;

    public Person(String name) {
        this.name = name;
        this.wallet = new Wallet();
    }

    public void receiveMoney(Money money) {
        wallet.putMoney(money);
    }

    public void giveMoney(Person other, Money money) {
        System.out.println(
                String.format("%s gives %s to %s", name, money, other.name));
        try {
            wallet.takeMoney(money);
            other.receiveMoney(money);
        } catch (NotEnoughMoneyException e) {
            System.out.println(
                    String.format("%s couldn't give %s to %s", name, money, other.name));
        }
    }

    public String toString() {
        return "Person " + name + " has wallet:\n" + wallet.toString();
    }
}
