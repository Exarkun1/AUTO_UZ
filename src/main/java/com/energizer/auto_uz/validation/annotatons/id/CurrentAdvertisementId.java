package com.energizer.auto_uz.validation.annotatons.id;

import com.energizer.auto_uz.validation.validators.id.CurrentAdvertisementIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrentAdvertisementIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentAdvertisementId {
    String message() default "Error";
    boolean value() default true;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
