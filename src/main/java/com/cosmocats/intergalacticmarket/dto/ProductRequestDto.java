package com.cosmocats.intergalacticmarket.dto;

import com.cosmocats.intergalacticmarket.validation.CosmicWordCheck;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(

        @NotBlank
        @Size(min = 3, max = 100)
        @CosmicWordCheck
        String name,

        @NotNull
        @DecimalMin("0.01")
        @DecimalMax("999999.99")
        @Digits(integer = 6, fraction = 2)
        BigDecimal price,

        @NotNull
        UUID categoryId
) {}
