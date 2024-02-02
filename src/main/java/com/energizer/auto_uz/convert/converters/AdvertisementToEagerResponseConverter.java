package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.ComponentResponse;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.models.characteristics.ComponentEntity;
import com.energizer.auto_uz.models.marks.Brand;
import com.energizer.auto_uz.models.marks.Generation;
import com.energizer.auto_uz.models.marks.Model;
import com.energizer.auto_uz.models.users.Advertisement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementToEagerResponseConverter implements Converter<Advertisement, EagerAdvertisementResponse> {
    @Override
    public EagerAdvertisementResponse convert(Advertisement source) {
        Generation gen = source.getGeneration();
        Model model = gen.getModel();
        Brand brand = model.getBrand();
        ComponentEntity corpus = source.getCorpus();
        ComponentEntity engine = source.getEngine();
        ComponentEntity drive = source.getDrive();
        ComponentEntity transmission = source.getTransmission();
        ComponentEntity modification = source.getModification();
        return new EagerAdvertisementResponse(
                source.getPerson().getId(),
                source.getMileage(),
                source.getDescription(),
                source.getPrice(),
                new MarkResponse(brand.getId(), brand.getName(), null),
                new MarkResponse(model.getId(), model.getName(), brand.getId()),
                new MarkResponse(gen.getId(), gen.getName(), model.getId()),
                new ComponentResponse(corpus.getId(), corpus.getName()),
                new ComponentResponse(engine.getId(), engine.getName()),
                new ComponentResponse(drive.getId(), drive.getName()),
                new ComponentResponse(transmission.getId(), transmission.getName()),
                new ComponentResponse(modification.getId(), modification.getName())
        );
    }
}
