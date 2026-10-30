package com.cosmocats.intergalacticmarket.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private static final List<String> COSMIC_TERMS = List.of("star", "galaxy", "comet", "nebula", "cosmic");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        String lowerCaseValue = value.toLowerCase();
        return COSMIC_TERMS.stream().anyMatch(lowerCaseValue::contains);
    }
}
