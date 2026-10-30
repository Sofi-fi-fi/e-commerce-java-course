package com.cosmocats.intergalacticmarket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordValidator.class)
@Documented
public @interface CosmicWordCheck {
    String message() default "Name must contain cosmic term (e.g. 'star', 'galaxy', 'comet')";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
