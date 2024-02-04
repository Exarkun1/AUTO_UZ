package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.id.ComponentId;
import com.energizer.auto_uz.validation.annotatons.id.GenerationId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AdvertisementUpdateRequest(
        @Min(value = 1, message = "Пробег не может быть меньше 1 км")
        @Max(value = 99999999, message = "Пробег не может быть больше 999999999 км")
        Long mileage,
        @Size(max = 1000, message = "Описание не может быть больше 1000 символов")
        String description,
        @Min(value = 500, message = "Цена не может быть меньше 500")
        @Max(value = 999999999999L, message = "Цена не может быть больше 999999999999")
        Long price,
        @GenerationId(message = "Не является id поколения")
        Long generation_id,
        @ComponentId(type = "corpus", message = "Не является id кузова")
        Long corpus_id,
        @ComponentId(type = "engine", message = "Не является id двигателя")
        Long engine_id,
        @ComponentId(type = "drive", message = "Не является id привода")
        Long drive_id,
        @ComponentId(type = "transmission", message = "Не является id коробки передач")
        Long transmission_id,
        @ComponentId(type = "modification", message = "Не является id модификации")
        Long modification_id
) {}
