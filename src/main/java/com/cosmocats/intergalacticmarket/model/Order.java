package com.cosmocats.intergalacticmarket.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> items;

    public Order() {}
    public Order(Long id, List<Product> items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<Product> getItems() { return items; }
    public void setItems(List<Product> items) { this.items = items; }
}
