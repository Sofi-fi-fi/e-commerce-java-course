package com.cosmocats.intergalacticmarket.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super("Product with id " + id + " not found");
    }
}
