package com.energizer.auto_uz.dto.response;

import java.util.List;

public record ModelResponse(
        Long id,
        String name,
        MarkResponse brand,
        List<MarkResponse> generations
) {}
