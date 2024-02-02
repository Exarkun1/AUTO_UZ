package com.energizer.auto_uz.dto.response;

public record LazyAdvertisementResponse(
        Long owner_id,
        Long mileage,
        String description,
        Long price,
        MarkResponse brand,
        MarkResponse model,
        MarkResponse generation
) {}
