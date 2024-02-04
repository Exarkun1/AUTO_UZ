package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.id.BrandId;
import com.energizer.auto_uz.validation.annotatons.StringList;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ModelListRequest(
        @NotNull(message = "Id бренда не может быть пустым")
        @BrandId(message = "Не является id бренда")
        Long brand_id,
        @NotEmpty(message = "Список не может быть пустым")
        @StringList(message = "Неверные данные в списке моделей")
        List<String> models
) {}
