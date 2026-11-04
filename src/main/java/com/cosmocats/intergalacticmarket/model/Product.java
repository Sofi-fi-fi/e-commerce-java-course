package com.cosmocats.intergalacticmarket.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private BigDecimal price;
    private Category category;

    public Product(UUID id, String name, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public UUID getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
