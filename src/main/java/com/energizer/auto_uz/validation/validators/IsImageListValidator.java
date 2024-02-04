package com.energizer.auto_uz.validation.validators;

import com.energizer.auto_uz.validation.annotatons.IsImageList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class IsImageListValidator implements ConstraintValidator<IsImageList, List<MultipartFile>> {
    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext constraintValidatorContext) {
        for(var file : files) {
            if (file.isEmpty()) return false;
            String filename = file.getOriginalFilename();
            if (filename == null) return false;
            int index = filename.lastIndexOf(".");
            if (index <= 0) return false;
            String extension = filename.substring(index + 1);
            if(!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))) return false;
        }
        return true;
    }
}
