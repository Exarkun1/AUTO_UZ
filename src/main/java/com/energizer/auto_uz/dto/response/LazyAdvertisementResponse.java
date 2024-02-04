package com.energizer.auto_uz.dto.response;

public record LazyAdvertisementResponse(
        Long id,
        Long mileage,
        String description,
        Long price,
        MarkResponse brand,
        MarkResponse model,
        MarkResponse generation,
        Long first_photo_id
) {}
