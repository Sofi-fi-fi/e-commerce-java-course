package com.cosmocats.intergalacticmarket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordValidator.class)
@Documented
public @interface CosmicWordCheck {
    String message() default "must contain a cosmic word (star, galaxy, comet, nebula, cosmic)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
