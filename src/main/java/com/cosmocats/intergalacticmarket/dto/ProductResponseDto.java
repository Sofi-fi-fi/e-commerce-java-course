package com.cosmocats.intergalacticmarket.dto;

import java.math.BigDecimal;

public record ProductResponseDto(Long id, String name, BigDecimal price, String categoryName) {}
