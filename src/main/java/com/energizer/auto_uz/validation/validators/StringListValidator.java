package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.validation.annotatons.StringList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class StringListValidator implements ConstraintValidator<StringList, List<String>> {
    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null) return true;
        for(var c : strings) {
            if(c == null || c.length() > 100 || c.length() <= 1) {
                return false;
            }
        }
        return true;
    }
}
