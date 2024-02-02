package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.dto.reques.GenerationRequest;
import com.energizer.auto_uz.validation.annotatons.GenerationList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class GenerationListValidator implements ConstraintValidator<GenerationList, List<GenerationRequest>> {
    @Override
    public boolean isValid(List<GenerationRequest> generationRequests, ConstraintValidatorContext constraintValidatorContext) {
        if(generationRequests == null) return true;
        for(var g : generationRequests) {
            if(g == null || g.name().length() > 100 || g.name().length() <= 1 || g.start_year() == null) {
                return false;
            }
            if(g.end_year() != null && (g.start_year() > g.end_year())) {
                return false;
            }
        }
        return true;
    }
}
