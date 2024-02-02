package com.energizer.auto_uz.validation.annotatons;

import com.energizer.auto_uz.validation.validators.ConfirmPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
