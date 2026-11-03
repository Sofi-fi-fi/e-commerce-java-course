package com.cosmocats.intergalacticmarket.dto;

import com.cosmocats.intergalacticmarket.validation.CosmicWordCheck;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(

        @NotNull(message = "Field 'name' must not be null")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        @CosmicWordCheck
        String name,

        @NotNull(message = "Field 'price' must not be null")
        @DecimalMin(value = "0.01", message = "Field 'price' must be greater than 0.")
        BigDecimal price,

        @NotNull
        UUID categoryId
) {}
