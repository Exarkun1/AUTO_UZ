package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.StringList;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BrandListRequest(
        @NotEmpty(message = "Список не может быть пустым")
        @StringList(message = "Не является списком брендов")
        List<String> brands
) {
}
