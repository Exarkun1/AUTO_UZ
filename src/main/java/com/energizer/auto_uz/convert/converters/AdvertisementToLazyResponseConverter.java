package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.LazyAdvertisementResponse;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.models.marks.Brand;
import com.energizer.auto_uz.models.marks.Generation;
import com.energizer.auto_uz.models.marks.Model;
import com.energizer.auto_uz.models.users.Advertisement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementToLazyResponseConverter implements Converter<Advertisement, LazyAdvertisementResponse> {
    @Override
    public LazyAdvertisementResponse convert(Advertisement source) {
        Generation gen = source.getGeneration();
        Model model = gen.getModel();
        Brand brand = model.getBrand();
        return new LazyAdvertisementResponse(
                source.getPerson().getId(),
                source.getMileage(),
                source.getDescription(),
                source.getPrice(),
                new MarkResponse(brand.getId(), brand.getName(), null),
                new MarkResponse(model.getId(), model.getName(), brand.getId()),
                new MarkResponse(gen.getId(), gen.getName(), model.getId())
        );
    }
}
