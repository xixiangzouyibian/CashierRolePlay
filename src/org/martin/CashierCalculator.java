package org.martin;

import java.math.BigDecimal;
import java.util.Map;

public class CashierCalculator {

    public static double getTotal(Map<Products, Integer> orders) {
        double total = 0;
        for (Products products : orders.keySet()) {
            total += products.getPrice() * orders.get(products);
        }
        return new BigDecimal(total).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
