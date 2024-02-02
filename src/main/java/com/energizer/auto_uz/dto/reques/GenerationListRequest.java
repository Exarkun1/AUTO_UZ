package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.GenerationList;
import com.energizer.auto_uz.validation.annotatons.ModelId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
public record GenerationListRequest(
        @NotNull(message = "Id модели не может быть пустым")
        @ModelId(message = "Не является id модели")
        Long model_id,
        @NotEmpty(message = "Список не может быть пустым")
        @GenerationList(message = "Некорректные данные в поколениях")
        List<GenerationRequest> generations
) {}
