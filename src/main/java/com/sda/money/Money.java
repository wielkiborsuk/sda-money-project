package com.sda.money;

import com.sda.money.exceptions.InvalidAmountException;
import com.sda.money.exceptions.NotEnoughMoneyException;
import com.sda.money.exceptions.WrongCurrencyException;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        validateMoney();
    }

    public Money(double amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    public Money(long amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    public Money(Currency currency) {
        this(BigDecimal.ZERO, currency);
    }

    public void addMoney(Money other) {
        checkCurrency(other);
        amount = amount.add(other.amount);
    }

    public void substractMoney(Money other) throws NotEnoughMoneyException {
        checkCurrency(other);
        if (this.amount.compareTo(other.amount) >= 0) {
            amount = amount.subtract(other.amount);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    public boolean isItEnough(Money debt) {
        return (currency.equals(debt.currency) &&
                amount.compareTo(debt.amount) >= 0);
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toString() {
        return String.format("%s %s", amount.toString(), currency.toString());
    }

    private void validateMoney() {
        if (BigDecimal.ZERO.compareTo(this.amount) > 0) {
            throw new InvalidAmountException();
        }
    }

    private void checkCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new WrongCurrencyException();
        }
    }
}
