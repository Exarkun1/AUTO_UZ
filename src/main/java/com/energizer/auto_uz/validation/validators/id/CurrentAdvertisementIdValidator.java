package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityConflictException;
import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.id.CurrentAdvertisementId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentAdvertisementIdValidator implements ConstraintValidator<CurrentAdvertisementId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(personService.containsUserAdvertisements(aLong, email) == value) return true;
        else throw new EntityConflictException();
    }

    @Override
    public void initialize(CurrentAdvertisementId constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    private boolean value;
    private final PersonService personService;
}
