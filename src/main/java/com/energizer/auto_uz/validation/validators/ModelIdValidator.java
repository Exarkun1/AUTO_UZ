package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.validation.annotatons.ModelId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ModelIdValidator implements ConstraintValidator<ModelId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong == null || markService.containsModelId(aLong);
    }
    private final MarkService markService;
}
