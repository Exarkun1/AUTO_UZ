package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email == null) return true;
        return personService.findByEmail(email).isEmpty();
    }
    private final PersonService personService;
}
