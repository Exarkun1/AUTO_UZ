package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.validation.annotatons.id.BrandId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandIdValidator implements ConstraintValidator<BrandId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong == null || markService.containsBrandId(aLong);
    }
    private final MarkService markService;
}
