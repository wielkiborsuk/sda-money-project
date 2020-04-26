package com.sda.money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Person frank = new Person("Frank");
        frank.receiveMoney(new Money(10, Currency.PLN));

        Person greg = new Person("Greg");
        greg.receiveMoney(new Money(20, Currency.PLN));

        LOGGER.debug(String.valueOf(frank));
        LOGGER.debug(String.valueOf(greg));

        greg.donate(frank, new Money(15, Currency.PLN));

        LOGGER.debug(String.valueOf(frank));
        LOGGER.debug(String.valueOf(greg));

        LOGGER.debug("=========================== item transactions ====================");

        frank.receiveItem("pan");
        greg.receiveItem("basket");

        List<Money> monies = Collections.singletonList(new Money(10, Currency.PLN));
        frank.addSellOffer(new Offer("pan", monies));

        List<Money> monies2 = Collections.singletonList(new Money(15, Currency.PLN));
        greg.addBuyOffer(new Offer("pan", monies2));
        greg.addSellOffer(new Offer("basket", monies));
        frank.addBuyOffer(new Offer("basket", monies2));

        greg.buy(frank, "pan");
        frank.buy(greg, "basket");

        LOGGER.debug(String.valueOf(frank));
        LOGGER.debug(String.valueOf(greg));
    }
}
