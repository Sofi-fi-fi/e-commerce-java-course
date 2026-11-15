package com.cosmocats.intergalacticmarket.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Cart {
    private final UUID id;
    private final Map<UUID, CartItem> items = new LinkedHashMap<>();

    public Cart(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void addItem(Product product, int quantity) {
        CartItem existing = items.get(product.getId());
        int newQuantity = existing == null ? quantity : existing.quantity() + quantity;
        items.put(product.getId(), new CartItem(product, newQuantity));
    }

    public void removeItem(UUID productId) {
        items.remove(productId);
    }

    public List<CartItem> getItems() {
        return List.copyOf(items.values());
    }

    public BigDecimal total() {
        return items.values().stream()
                .map(CartItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
