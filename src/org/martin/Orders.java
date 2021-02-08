package org.martin;

import java.util.HashMap;
import java.util.Map;

public class Orders {

    private static final String BULK_SEPARATOR = ",";
    private Map<ProductWithPrice, Integer> orders;

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
        ProductWithPrice pwp = ProductWithPrice.getInstance(productName);
        if (pwp.equals(ProductWithPrice.UNRECOGNIZED)) {
            System.out.println(String.format("Unrecognized product: %s, scan failed...", productName));
            return;
        }
        orders.put(pwp, orders.getOrDefault(pwp, 0)+1);
    }

    // ## requirement 2
    public void cancel(String productName, Integer quantity) {
        ProductWithPrice pwp = ProductWithPrice.getInstance(productName);
        if (pwp.equals(ProductWithPrice.UNRECOGNIZED)) {
            System.out.println(String.format("Unrecognized product: %s, cancel failed...", productName));
            return;
        }
        if (orders.containsKey(pwp)) {
            if (quantity == null) {
                orders.remove(pwp);
            } else {
                int diff = orders.get(pwp) - quantity;
                if (diff <= 0) {
                    orders.remove(pwp);
                } else {
                    orders.put(pwp, diff);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder printer = new StringBuilder("=================== Orders ====================");
        for (ProductWithPrice pwp : orders.keySet()) {
            printer.append("Product Name:").append(pwp.name()).append(BULK_SEPARATOR)
                    .append("Quantity:").append(orders.get(pwp)).append(BULK_SEPARATOR)
                    .append("Price:").append(pwp.getPrice()).append(BULK_SEPARATOR)
                    .append("Subtotal:").append(orders.get(pwp) * pwp.getPrice()).append(BULK_SEPARATOR)
                    .append("\n");
        }
        printer.append("Total:").append(CashierCalculator.getTotal(orders));
        return printer.toString();
    }
}
