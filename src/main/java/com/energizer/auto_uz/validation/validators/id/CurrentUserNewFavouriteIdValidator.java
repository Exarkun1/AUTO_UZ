package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.id.CurrentUserNewFavouriteId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentUserNewFavouriteIdValidator implements ConstraintValidator<CurrentUserNewFavouriteId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(personService.containsAdvertisement(aLong)
                && !personService.containsUserAdvertisements(aLong, email)
                && !personService.containsUserFavourites(aLong, email)) return true;
        else throw new EntityNotFoundException();
    }

    private final PersonService personService;
}