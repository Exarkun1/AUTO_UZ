package com.energizer.auto_uz.dto.response;

import java.util.List;

public record PubliclyUserResponse(
        Long id,
        String username,
        String role,
        Double avg_score,
        List<Long> advertisements_id,
        List<Long> feedbacks_id
) {}
