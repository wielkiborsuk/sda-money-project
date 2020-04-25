package com.sda.money;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person frank = new Person("Frank");
        frank.receiveMoney(new Money(10, Currency.PLN));

        Person greg = new Person("Greg");
        greg.receiveMoney(new Money(20, Currency.PLN));

        System.out.println(frank);
        System.out.println(greg);

        greg.giveMoney(frank, new Money(15, Currency.PLN));

        System.out.println(frank);
        System.out.println(greg);

        System.out.println("=========================== item transactions ====================");

        frank.receiveItem("pan");
        greg.receiveItem("basket");
        greg.receiveMoney(new Money(15, Currency.PLN));

        List<Money> monies = Arrays.asList(new Money(10, Currency.PLN));
        frank.addSellOffer(new Offer("pan", monies));

        List<Money> monies2 = Arrays.asList(new Money(15, Currency.PLN));
        greg.addBuyOffer(new Offer("pan", monies2));

        greg.buy(frank, "pan");

        System.out.println(frank);
        System.out.println(greg);
    }
}
