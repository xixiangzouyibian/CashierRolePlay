package org.martin;

import java.util.EnumSet;

public enum ProductWithPrice {

    APPLE(4.0),
    MILK(9.41),
    EGGS(5.25),
    COLA(12.10),
    COLAZERO(11.15),
    UNRECOGNIZED(-1.0);

    private double price;
    private static final String BLANK = " ";

    ProductWithPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static ProductWithPrice getInstance(String produceName) {
        for (ProductWithPrice pwp : EnumSet.allOf(ProductWithPrice.class)) {
            if (pwp.name().equals(format(produceName))) {
                return pwp;
            }
        }
        return ProductWithPrice.UNRECOGNIZED;
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
