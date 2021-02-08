package org.martin;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Orders {

    private static final String BULK_SEPARATOR = ",";
    private Map<Products, Integer> orders;

    public Orders() {
        orders = new HashMap<>();
    }

    // ## requirement 1
    public void bulkScan(String productNames) {
        if (productNames.contains(BULK_SEPARATOR)) {
            for (String pn : productNames.split(BULK_SEPARATOR)) {
                scan(pn);
            }
        } else {
            scan(productNames);
        }
    }

    // ## requirement 1
    private void scan(String productName) {
        Products pwp = Products.getInstance(productName);
        if (pwp.equals(Products.UNRECOGNIZED)) {
            System.out.println(String.format("Unrecognized product: %s, ignored scanning", productName));
            return;
        }
        orders.put(pwp, orders.getOrDefault(pwp, 0)+1);
    }

    // ## requirement 2
    public void cancel(String productName, Integer quantity) {
        Products pwp = Products.getInstance(productName);
        if (pwp.equals(Products.UNRECOGNIZED)) {
            System.out.println(String.format("Unrecognized product: %s, cancel failed", productName));
            return;
        }
        if (orders.containsKey(pwp)) {
            if (quantity == null) {
                orders.remove(pwp);
            } else if (quantity <= 0) {
                System.out.println("Operation failed: Quantity should be greater than 0");
            }else {
                int diff = orders.get(pwp) - quantity;
                if (diff <= 0) {
                    orders.remove(pwp);
                } else {
                    orders.put(pwp, diff);
                }
            }
        }
    }

    // ## Requirement 4
    public void discount(String productName, String percentage) {
        Products products = Products.getInstance(productName);
        if (products.equals(Products.UNRECOGNIZED)) {
            System.out.println(String.format("Unrecognized product: %s, discount failed", productName));
            return;
        }
        NumberFormat nf= NumberFormat.getPercentInstance();
        try {
            Number number = nf.parse(percentage);
            double value = number.doubleValue();
            if (value < 0.0 || value > 1.0) {
                System.out.println("Please input the correct percentage number, should be limited in (0%, 100%)");
                return;
            }
            products.setPrice(products.getPrice() * (1-number.doubleValue()));
            products.setIsDiscount(true);
        } catch (ParseException e) {
            System.out.println("Please typo the correct percentage number");
        }
    }

    // ## requirement 5 & 6 & 7
    public String getSuggestionIfSpecialOffers(Products products) {
        int[][] offers = products.getSpecialOffers();
        if (offers != null) {
            Products[] proArray = Products.values();
            Products buyProd = proArray[offers[0][0]];
            Products freeProd = proArray[offers[1][0]];

            if (offers[0][1] > orders.get(products)) {
                return String.format(" (Special offering: buy %s of %s, free %s of %s", offers[0][1], buyProd.name(), offers[1][1], freeProd.name());
            } else {
                int boughtNum = orders.get(products);
                int freeNum;
                if (offers[0][0] == offers[1][0]) { // same product
                    freeNum = (boughtNum / offers[0][1]) * (offers[1][1] - offers[0][1]);
                } else { // different product
                    freeNum = (boughtNum / offers[0][1]) * offers[1][1];
                }
                return String.format(" (Special offering: buy %s of %s, free %s of %s, and free to give: %s", offers[0][1], buyProd.name(), offers[1][1], freeProd.name(), freeNum);
            }
        }
        return "";
    }

    // ## requirement 3
    @Override
    public String toString() {
        StringBuilder printer =
                new StringBuilder("===================== Receipt ======================\n");
        for (Products products : orders.keySet()) {
            printer.append("Product Name:").append(products.name()).append(BULK_SEPARATOR)
                    .append("Quantity:").append(orders.get(products)).append(BULK_SEPARATOR)
                    .append(products.isDiscount() ? "*Discount Price:": "Price:").append(products.getPrice()).append(BULK_SEPARATOR)
                    .append("Subtotal:").append(orders.get(products) * products.getPrice())
                    .append("\n");
            if (getSuggestionIfSpecialOffers(products).length() > 0) {
                printer.append(getSuggestionIfSpecialOffers(products)).append("\n");
            }
        }
        printer.append("Total:").append(CashierCalculator.getTotal(orders)).append("\n");
        printer.append("======================= END ========================\n");
        return printer.toString();
    }
}
