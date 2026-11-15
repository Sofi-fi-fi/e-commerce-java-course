package com.cosmocats.intergalacticmarket.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<OrderItem> items;
    private final BigDecimal total;

    public Order(UUID id, List<OrderItem> items) {
        this.id = id;
        this.items = List.copyOf(items);
        this.total = this.items.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Order fromCart(UUID id, Cart cart) {
        List<OrderItem> items = cart.getItems().stream()
                .map(item -> new OrderItem(item.product().getId(), item.quantity(), item.product().getPrice()))
                .toList();
        return new Order(id, items);
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
