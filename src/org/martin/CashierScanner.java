package org.martin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Run this class to scan customer products
 */
public class CashierScanner {

    private static final String BULK_SEPARATOR = ",";

    public static final Map<ProductWithPrice, Integer> orders = new HashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String productNames = scanner.nextLine();
                System.out.println("Di, Scan: " + productNames);
                if (productNames.contains(BULK_SEPARATOR)) {
                    for (String pn : productNames.split(BULK_SEPARATOR)) {
                        putOrder(pn);
                    }
                } else {
                    putOrder(productNames);
                }
                System.out.println("Current Total: " + CashierCalculator.getTotal(orders));
            }
        }).start();
    }

    private static void putOrder(String productName) {
        ProductWithPrice pwp = ProductWithPrice.getInstance(productName);
        if (pwp.equals(ProductWithPrice.UNRECOGNIZED)) {
            System.out.println("Unrecognized product: " + productName);
            return;
        }
        orders.put(pwp, orders.getOrDefault(pwp, 0)+1);
    }
}
