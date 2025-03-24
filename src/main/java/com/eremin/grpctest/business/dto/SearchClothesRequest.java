package com.eremin.grpctest.business.dto;

public record SearchClothesRequest(
        String color,
        String brand,
        String season,
        String gender,
        String category
) {
}
