package com.energizer.auto_uz.dto.reques;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(
        @NotNull(message = "Оценка не может быть пустой")
        @Min(value = 1, message = "Оценка не может быть ниже 1")
        @Max(value = 5, message = "Оценка не может быть выше 5")
        Integer score,
        @Size(max = 1000, message = "Сообщение не может иметь более 1000 символов")
        String note
) {}
