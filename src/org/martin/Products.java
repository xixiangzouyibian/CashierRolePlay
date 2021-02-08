package org.martin;

import java.util.EnumSet;

public enum Products {

    APPLE(4.0, new int[] {3,4}, false),
    MILK(9.41, null, false),
    EGGS(5.25, null, false),
    COLA(12.10, null, false),
    COLAZERO(11.15, null, false),
    UNRECOGNIZED(-1.0, null, false);

    private double price;
    private int[] offers;
    private boolean isDiscount = false;
    private static final String BLANK = " ";

    Products(double price, int[] offers, boolean isDiscount) {
        this.price = price;
        this.offers = offers;
        this.isDiscount = isDiscount;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isDiscount() {
        return isDiscount;
    }
    // ## requirement 6
    public void setIsDiscount(boolean isDiscount) {
        this.isDiscount = isDiscount;
    }
    // ## requirement 5
    public int[] getSpecialOffers() {
        return offers;
    }

    public static Products getInstance(String produceName) {
        for (Products products : EnumSet.allOf(Products.class)) {
            if (products.name().equals(format(produceName))) {
                return products;
            }
        }
        return Products.UNRECOGNIZED;
    }

    private static String format(String name) {
        if (!name.contains(BLANK)) {
            return name.trim().toUpperCase();
        }
        StringBuilder nameBuilder = new StringBuilder();
        for (String nameWord : name.split(BLANK)) {
            if (nameWord.equals(BLANK)) continue;
            nameBuilder.append(nameWord.trim().toUpperCase());
        }
        return nameBuilder.toString();
    }
}
