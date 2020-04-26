package com.sda.money;

import com.sda.money.exceptions.ItemNotFoundException;
import com.sda.money.exceptions.NotEnoughMoneyException;
import com.sda.money.exceptions.OfferMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

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

    public void donate(Person other, Money money) {
        LOGGER.info("{} gives {} to {}", name, money, other.name);

        try {
            giveMoney(other, money);
        } catch (NotEnoughMoneyException e) {
            LOGGER.warn("{} couldn't give {} to {}",
                    name, money, other.name);
        }
    }

    private void giveMoney(Person other, Money money) throws NotEnoughMoneyException {
        wallet.takeMoney(money);
        other.receiveMoney(money);
    }

    public void receiveItem(String item) {
        myItems.add(item);
    }

    public void giveItem(Person other, String item) throws ItemNotFoundException {
        if (myItems.remove(item)) {
            other.receiveItem(item);
        } else {
            throw new ItemNotFoundException();
        }
    }

    public void addSellOffer(Offer offer) {
        willSell.add(offer);
    }

    public void addBuyOffer(Offer offer) {
        willBuy.add(offer);
    }

    public void buy(Person other, String item) {
        Offer sellOffer = other.getSellOffer(item);
        Offer buyOffer = this.getBuyOffer(item);
        Money matchingPrice = null;
        try {
            matchingPrice = buyOffer.getMatchingOffer(sellOffer);

            LOGGER.info("{} buys {} from {}", name, item, other.name);
            wallet.takeMoney(matchingPrice);
            other.giveItem(this, item);
            other.receiveMoney(matchingPrice);
        } catch (OfferMismatchException mismatch) {
            LOGGER.warn("Could not find an offer match");
        } catch (NotEnoughMoneyException iAmPoor) {
            LOGGER.warn("{} couldn't pay for the item {}", name, item);
        } catch (ItemNotFoundException itemMissing) {
            LOGGER.warn("{} didn't give item {} to {}", other.name, item, name);
            this.receiveMoney(matchingPrice);
        }
    }

    private Offer getSellOffer(String item) {
        for (Offer offer : willSell) {
            if (item.equals(offer.itemName)) {
                return offer;
            }
        }
        return Offer.NONE_OFFER;
    }

    private Offer getBuyOffer(String item) {
        for (Offer offer : willBuy) {
            if (item.equals(offer.itemName)) {
                return offer;
            }
        }
        return Offer.NONE_OFFER;
    }

    public String toString() {
        return "Person: " + name + ", Wallet: " + wallet + ", Items: " + myItems;
    }
}
