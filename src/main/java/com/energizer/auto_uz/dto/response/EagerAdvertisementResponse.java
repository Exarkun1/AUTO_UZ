package com.energizer.auto_uz.dto.response;

import java.util.List;

public record EagerAdvertisementResponse(
        Long id,
        Long owner_id,
        Long mileage,
        String description,
        Long price,
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
