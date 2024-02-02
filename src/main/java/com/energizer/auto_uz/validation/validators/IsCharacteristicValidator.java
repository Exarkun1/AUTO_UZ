package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.validation.annotatons.IsCharacteristic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsCharacteristicValidator implements ConstraintValidator<IsCharacteristic, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) return false;
        return characteristicService.containsCharacteristic(s);
    }
    private final CharacteristicService characteristicService;
}
