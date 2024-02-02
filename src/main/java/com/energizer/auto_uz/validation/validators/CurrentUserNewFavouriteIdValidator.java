package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.CurrentUserNewFavouriteId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentUserNewFavouriteIdValidator implements ConstraintValidator<CurrentUserNewFavouriteId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return personService.containAdvertisement(aLong)
                && !personService.containsUserAdvertisements(aLong, email)
                && !personService.containsUserFavourites(aLong, email);
    }

    private final PersonService personService;
}
