package com.eremin.grpctest.business.mapper;

import com.eremin.grpctest.business.dto.ClothesDto;
import com.eremin.grpctest.output.db.model.Clothes;

public class ClothesMapper {

    public static ClothesDto toDto(Clothes clothes) {
        return new ClothesDto(
                clothes.getId(),
                clothes.getName(),
                clothes.getGender(),
                clothes.getCategory(),
                clothes.getSeason(),
                clothes.getBrand(),
                clothes.getColor()
        );
    }

}
