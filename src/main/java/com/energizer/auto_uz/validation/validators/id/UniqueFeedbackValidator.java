package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityConflictException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.id.UniqueFeedback;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
@RequiredArgsConstructor
public class UniqueFeedbackValidator implements ConstraintValidator<UniqueFeedback, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!personService.containsFeedbackWithUserAndOwner(aLong, email)) return true;
        else throw new EntityConflictException();
    }

    private final PersonService personService;
}
