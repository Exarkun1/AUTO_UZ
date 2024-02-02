package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.reques.GenerationRequest;
import com.energizer.auto_uz.models.marks.Generation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestToGenerationConverter implements Converter<GenerationRequest, Generation> {
    @Override
    public Generation convert(GenerationRequest source) {
        Generation generation = new Generation();
        generation.setName(source.name());
        generation.setStartYear(source.start_year());
        generation.setEndYear(source.end_year());
        return generation;
    }
}
