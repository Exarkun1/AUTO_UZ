package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.validation.annotatons.id.ComponentId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentIdValidator implements ConstraintValidator<ComponentId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if(aLong == null || characteristicService.containComponentByType(aLong, type)) return true;
        else throw new EntityNotFoundException();
    }

    @Override
    public void initialize(ComponentId constraintAnnotation) {
        type = constraintAnnotation.type();
    }
    private String type;
    private final CharacteristicService characteristicService;
}
