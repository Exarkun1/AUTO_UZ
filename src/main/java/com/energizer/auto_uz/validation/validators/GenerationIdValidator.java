package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.validation.annotatons.GenerationId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerationIdValidator implements ConstraintValidator<GenerationId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong == null || markService.containsGenerationId(aLong);
    }
    private final MarkService markService;
}
