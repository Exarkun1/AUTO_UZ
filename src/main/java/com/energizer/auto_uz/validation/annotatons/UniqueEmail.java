package com.energizer.auto_uz.validation.annotatons;

import com.energizer.auto_uz.validation.validators.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
