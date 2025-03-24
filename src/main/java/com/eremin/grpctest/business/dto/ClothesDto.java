package com.eremin.grpctest.business.dto;

import java.util.UUID;

public record ClothesDto(
        UUID id,
        String name,
        String gender,
        String category,
        String season,
        String brand,
        String color
) {
}
