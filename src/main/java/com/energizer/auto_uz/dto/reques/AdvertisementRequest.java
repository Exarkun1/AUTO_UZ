package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.ComponentId;
import com.energizer.auto_uz.validation.annotatons.GenerationId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdvertisementRequest(
        @Min(value = 1, message = "Пробег не может быть меньше 1 км")
        @Max(value = 99999999, message = "Пробег не может быть больше 999999999 км")
        Long mileage,
        @Size(max = 1000, message = "Описание не может быть больше 1000 символов")
        String description,
        @NotNull(message = "Не может быть пустым")
        @Min(value = 500, message = "Цена не может быть меньше 500")
        @Max(value = 999999999999L, message = "Цена не может быть больше 999999999999")
        Long price,
        @NotNull(message = "Не может быть пустым")
        @GenerationId(message = "Не является id поколения")
        Long generation_id,
        @NotNull(message = "Не может быть пустым")
        @ComponentId(type = "corpus", message = "Не является id кузова")
        Long corpus_id,
        @NotNull(message = "Не может быть пустым")
        @ComponentId(type = "engine", message = "Не является id двигателя")
        Long engine_id,
        @NotNull(message = "Не может быть пустым")
        @ComponentId(type = "drive", message = "Не является id привода")
        Long drive_id,
        @NotNull(message = "Не может быть пустым")
        @ComponentId(type = "transmission", message = "Не является id коробки передач")
        Long transmission_id,
        @NotNull(message = "Не может быть пустым")
        @ComponentId(type = "modification", message = "Не является id модификации")
        Long modification_id
) {}