package com.energizer.auto_uz.dto.reques;
public record GenerationRequest(
        String name,
        Integer start_year,
        Integer end_year
) {}
