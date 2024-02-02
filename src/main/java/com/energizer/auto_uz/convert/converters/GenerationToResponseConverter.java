package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.GenerationResponse;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.models.marks.Generation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenerationToResponseConverter implements Converter<Generation, GenerationResponse> {
    @Override
    public GenerationResponse convert(Generation source) {
        return new GenerationResponse(
                source.getId(),
                source.getName(),
                source.getStartYear(),
                source.getEndYear(),
                new MarkResponse(
                        source.getModel().getId(),
                        source.getModel().getName(),
                        source.getModel().getBrand().getId()
                )
        );
    }
}
