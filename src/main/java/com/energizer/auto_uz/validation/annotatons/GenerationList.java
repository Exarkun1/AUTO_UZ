package com.energizer.auto_uz.validation.annotatons;

import com.energizer.auto_uz.validation.validators.GenerationListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GenerationListValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerationList {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
