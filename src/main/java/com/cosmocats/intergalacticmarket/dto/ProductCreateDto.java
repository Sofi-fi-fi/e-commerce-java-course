package com.cosmocats.intergalacticmarket.dto;

import com.cosmocats.intergalacticmarket.validation.CosmicWordCheck;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductCreateDto {

    @NotNull(message = "Field 'name' must not be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @CosmicWordCheck
    private String name;

    @NotNull(message = "Field 'price' must not be null")
    @DecimalMin(value = "0.01", message = "Field 'price' must be greater than 0.")
    private BigDecimal price;

    private String categoryName;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
