package com.cosmocats.intergalacticmarket.model;

import java.math.BigDecimal;

public record CartItem(Product product, int quantity) {

    public CartItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    public BigDecimal subtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
