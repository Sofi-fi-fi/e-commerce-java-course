package com.cosmocats.intergalacticmarket.model;

import java.util.List;

public record ProductPage(List<Product> items, long totalElements) {

    public ProductPage {
        items = List.copyOf(items);
    }
}
