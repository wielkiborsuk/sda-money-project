package com.sda.money;

import com.sda.money.exceptions.OfferMismatchException;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    String itemName;
    List<Money> prices;

    public static Offer NONE_OFFER = new Offer("none offer", new ArrayList<>());

    public Offer(String itemName, List<Money> prices) {
        this.itemName = itemName;
        this.prices = prices;
    }

    public Money getMatchingOffer(Offer sellOffer) throws OfferMismatchException {
        for (Money willBuy : prices) {
            for (Money willSell : sellOffer.prices) {
                if (willBuy.isItEnough(willSell)) {
                    return willSell;
                }
            }
        }
        throw new OfferMismatchException();
    }
}
