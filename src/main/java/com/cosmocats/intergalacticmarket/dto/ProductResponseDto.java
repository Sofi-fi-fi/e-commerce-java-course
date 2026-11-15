package com.cosmocats.intergalacticmarket.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(UUID id, String name, BigDecimal price, UUID categoryId, String categoryName) {}
