package com.sda.money;

import java.util.List;

public class Offer {
    String itemName;
    List<Money> prices;

    public Offer(String itemName, List<Money> prices) {
        this.itemName = itemName;
        this.prices = prices;
    }

    public Money getMatchingOffer(Offer sellOffer) {
        for (Money willBuy : prices) {
            for (Money willSell : sellOffer.prices) {
                if (willBuy.isItEnough(willSell)) {
                    return willSell;
                }
            }
        }
        return null;
    }
}
