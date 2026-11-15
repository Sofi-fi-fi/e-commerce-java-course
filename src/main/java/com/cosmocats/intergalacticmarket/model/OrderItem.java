package com.cosmocats.intergalacticmarket.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(UUID productId, int quantity, BigDecimal priceAtPurchase) {

    public OrderItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    public BigDecimal subtotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
}
