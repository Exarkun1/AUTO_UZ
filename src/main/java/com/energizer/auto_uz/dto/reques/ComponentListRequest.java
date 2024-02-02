package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.IsCharacteristic;
import com.energizer.auto_uz.validation.annotatons.StringList;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ComponentListRequest(
        @NotEmpty(message = "Тип не может быть пустым")
        @IsCharacteristic(message = "Не является характеристикой")
        String type,
        @NotEmpty(message = "Список не может быть пустым")
        @StringList(message = "Некорректные данные в списке компонентов")
        List<String> components
) {}
