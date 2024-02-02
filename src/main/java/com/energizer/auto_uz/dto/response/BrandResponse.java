package com.energizer.auto_uz.dto.response;

import java.util.List;

public record BrandResponse(
        Long id,
        String name,
        List<MarkResponse> models
) {}
