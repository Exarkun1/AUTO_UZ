package com.energizer.auto_uz.dto.response;

import java.util.Date;

public record FeedbackResponse(
        Long id,
        Integer score,
        String note,
        Date time_of_create,
        Long user_id,
        Long owner_id
) {}
