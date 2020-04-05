package com.sda.money;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Person frank = new Person("Frank");
        frank.receiveMoney(new Money(10));

        Person greg = new Person("Greg");
        greg.receiveMoney(new Money(20));

        System.out.println(frank);
        System.out.println(greg);

        greg.giveMoney(frank, new Money(45));

        System.out.println(frank);
        System.out.println(greg);
    }
}
