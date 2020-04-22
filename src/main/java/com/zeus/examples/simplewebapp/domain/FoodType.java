package com.zeus.examples.simplewebapp.domain;

public enum FoodType {
    BEVERAGE("beverage"),
    FOOD("food"),
    SODA_CAN("sodaCan"),
    OTHER("other");

    @Override
    public String toString() {
        return this.pathname;
    }

    public final String pathname;

    FoodType(String pathname) {
        this.pathname = pathname;
    }
}
