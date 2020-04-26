package com.sda.money;

import com.sda.money.exceptions.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private Map<Currency, Money> moneyMap = new HashMap<>();

    public void putMoney(Money other) {
        getMoneyInCurrency(other.getCurrency()).addMoney(other);
    }

    public void takeMoney(Money other) throws NotEnoughMoneyException {
        getMoneyInCurrency(other.getCurrency()).substractMoney(other);
    }

    private Money getMoneyInCurrency(Currency currency) {
        if (!moneyMap.containsKey(currency)) {
            moneyMap.put(currency, new Money(currency));
        }
        return moneyMap.get(currency);
    }

    public String toString() {
        return String.valueOf(moneyMap);
    }
}
