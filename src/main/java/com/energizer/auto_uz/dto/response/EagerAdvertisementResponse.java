package com.energizer.auto_uz.dto.response;

public record EagerAdvertisementResponse(
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
        ComponentResponse modification
) {}
