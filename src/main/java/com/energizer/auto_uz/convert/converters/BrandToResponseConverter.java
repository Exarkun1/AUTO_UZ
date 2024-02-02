package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.BrandResponse;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.models.marks.Brand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class BrandToResponseConverter implements Converter<Brand, BrandResponse> {
    @Override
    public BrandResponse convert(Brand source) {
        return new BrandResponse(
                source.getId(),
                source.getName(),
                source.getModels().stream()
                        .map(m -> new MarkResponse(m.getId(), m.getName(), source.getId())).toList()
        );
    }
}
