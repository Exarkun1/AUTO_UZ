package com.energizer.auto_uz.utils;

import com.energizer.auto_uz.dto.errors.DataErrors;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ErrorsUtil {
    public DataErrors getDataErrors(BindingResult errors) {
        Map<String, List<String>> fieldErrors = new HashMap<>();
        List<String> globalErrors = new ArrayList<>();
        for(var error : errors.getFieldErrors()) {
            if(!fieldErrors.containsKey(error.getField())) {
                fieldErrors.put(error.getField(), new ArrayList<>());
            }
            fieldErrors.get(error.getField()).add(error.getDefaultMessage());
        }
        for(var error : errors.getGlobalErrors()) {
            globalErrors.add(error.getDefaultMessage());
        }
        return new DataErrors(fieldErrors, globalErrors);
    }
    public DataErrors getDateErrors(String message) {
        return new DataErrors(Map.of(), List.of(message));
    }
}
