package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.dto.response.ModelResponse;
import com.energizer.auto_uz.models.marks.Model;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModelToResponseConverter implements Converter<Model, ModelResponse> {
    @Override
    public ModelResponse convert(Model source) {
        return new ModelResponse(
                source.getId(),
                source.getName(),
                new MarkResponse(
                        source.getBrand().getId(),
                        source.getBrand().getName(),
                        null),
                source.getGenerations().stream()
                        .map(m -> new MarkResponse(m.getId(), m.getName(), source.getId())).toList()
        );
    }
}
