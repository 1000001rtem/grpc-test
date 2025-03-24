package com.eremin.grpctest.input.mapper;

import com.eremin.grpctest.Clothes;
import com.eremin.grpctest.business.dto.ClothesDto;
import com.eremin.grpctest.business.dto.CreateClothesRequest;
import com.eremin.grpctest.business.dto.SearchClothesRequest;

public class ClothesMapper {

    public static CreateClothesRequest toDto(Clothes.CreateClothesRequest request) {
        return new CreateClothesRequest(
                request.getName(),
                request.getColor(),
                request.getBrand(),
                request.getSeason(),
                request.getGender(),
                request.getCategory()
        );
    }

    public static SearchClothesRequest toSearchDto(Clothes.SearchClothesRequest request) {
        return new SearchClothesRequest(
                request.getColor().isBlank() ? null : request.getColor(),
                request.getBrand().isBlank() ? null : request.getBrand(),
                request.getSeason().isBlank() ? null : request.getSeason(),
                request.getGender().isBlank() ? null : request.getGender(),
                request.getCategory().isBlank() ? null : request.getCategory()
        );
    }

    public static Clothes.ClothesDto toResponse(ClothesDto clothes) {
        return Clothes.ClothesDto.newBuilder()
                .setId(clothes.id().toString())
                .setName(clothes.name())
                .setBrand(clothes.brand())
                .setCategory(clothes.category())
                .setColor(clothes.color())
                .setGender(clothes.gender())
                .build();
    }
}
