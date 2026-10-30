package com.cosmocats.intergalacticmarket.dto;

import java.math.BigDecimal;

public class ProductResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String categoryName;

    public ProductResponseDto() {}

    public ProductResponseDto(Long id, String name, BigDecimal price, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
