package com.sda.money;

import com.sda.money.exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private Wallet wallet;

    private List<String> myItems = new ArrayList<>();
    private List<Offer> willBuy = new ArrayList<>();
    private List<Offer> willSell = new ArrayList<>();

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

    public void buy(Person other, String item) {
        Offer sellOffer = other.getSellOffer(item);
        Offer buyOffer = this.getBuyOffer(item);
        // TODO
    }

    private Offer getSellOffer(String item) {
        for (Offer offer : willSell) {
            if (item.equals(offer.itemName)) {
                return offer;
            }
        }
        return null;
    }

    private Offer getBuyOffer(String item) {
        for (Offer offer : willBuy) {
            if (item.equals(offer.itemName)) {
                return offer;
            }
        }
        return null;
    }

    public String toString() {
        return "Person " + name + " has wallet:\n" + wallet.toString();
    }
}
