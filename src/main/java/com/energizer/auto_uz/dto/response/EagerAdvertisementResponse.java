package com.energizer.auto_uz.dto.response;

import java.util.Date;
import java.util.List;

public record EagerAdvertisementResponse(
        Long id,
        Long owner_id,
        Long mileage,
        String description,
        Long price,
        Date date_of_create,
        MarkResponse brand,
        MarkResponse model,
        MarkResponse generation,
        ComponentResponse corpus,
        ComponentResponse engine,
        ComponentResponse drive,
        ComponentResponse transmission,
        ComponentResponse modification,
        List<Long> photos_id
) {}
