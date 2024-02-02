package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.dto.reques.RegisterRequest;
import com.energizer.auto_uz.validation.annotatons.ConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, RegisterRequest> {
    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.password().equals(registerRequest.confirmPassword());
    }
}
