package org.martin;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;

public class CashierCalculator {

    public static double getTotal(Map<ProductWithPrice, Integer> orders) {
        double total = 0;
        for (ProductWithPrice pwp : orders.keySet()) {
            total += pwp.getPrice() * orders.get(pwp);
        }
        return new BigDecimal(total).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
