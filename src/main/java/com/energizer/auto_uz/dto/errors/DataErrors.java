package com.energizer.auto_uz.dto.errors;

import java.util.List;
import java.util.Map;

public record DataErrors (
        Map<String, List<String>> fieldErrors,
        List<String> globalErrors
) {}
