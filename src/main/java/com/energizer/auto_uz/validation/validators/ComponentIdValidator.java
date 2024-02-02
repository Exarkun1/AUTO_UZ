package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.validation.annotatons.ComponentId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentIdValidator implements ConstraintValidator<ComponentId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if(aLong == null) return true;
        return characteristicService.containComponentByType(aLong, type);
    }

    @Override
    public void initialize(ComponentId constraintAnnotation) {
        type = constraintAnnotation.type();
    }
    private String type;
    private final CharacteristicService characteristicService;
}
