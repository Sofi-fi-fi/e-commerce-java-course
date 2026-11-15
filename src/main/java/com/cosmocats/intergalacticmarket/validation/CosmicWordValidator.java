package com.cosmocats.intergalacticmarket.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private static final Set<String> COSMIC_TERMS = Set.of("star", "galaxy", "comet", "nebula", "cosmic");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return Arrays.stream(value.toLowerCase(Locale.ROOT).split("[^a-z]+"))
                .anyMatch(COSMIC_TERMS::contains);
    }
}
