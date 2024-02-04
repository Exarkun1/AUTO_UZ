package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.validation.annotatons.id.GenerationId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerationIdValidator implements ConstraintValidator<GenerationId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if(aLong == null || markService.containsGenerationId(aLong)) return true;
        else throw new EntityNotFoundException();
    }
    private final MarkService markService;
}
