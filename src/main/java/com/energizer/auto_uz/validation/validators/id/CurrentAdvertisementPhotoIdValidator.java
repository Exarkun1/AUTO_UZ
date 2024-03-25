package com.energizer.auto_uz.validation.validators.id;

import com.energizer.auto_uz.exceptions.EntityConflictException;
import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.validation.annotatons.id.CurrentAdvertisementPhotoId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class CurrentAdvertisementPhotoIdValidator implements ConstraintValidator<CurrentAdvertisementPhotoId, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(personService.containsUserAdvertisementPhotos(aLong, email)) return true;
        else throw new EntityConflictException();
    }
    private final PersonService personService;
}
