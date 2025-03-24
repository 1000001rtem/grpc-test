package com.eremin.grpctest.business.dto;

public record CreateClothesRequest(
        String name,
        String color,
        String brand,
        String season,
        String gender,
        String category
) {
}
