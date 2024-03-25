package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityConflictException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.id.CurrentFeedbackId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentFeedbackIdValidator implements ConstraintValidator<CurrentFeedbackId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(personService.containsFeedbackWithOwner(aLong, email) == value) return true;
        else throw new EntityConflictException();
    }
    @Override
    public void initialize(CurrentFeedbackId constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    private boolean value;
    private final PersonService personService;
}
