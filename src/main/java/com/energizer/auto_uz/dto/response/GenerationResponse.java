package com.energizer.auto_uz.dto.response;

public record GenerationResponse(
        Long id,
        String name,
        Integer start_year,
        Integer end_year,
        MarkResponse model
) {}
