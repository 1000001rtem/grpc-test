package com.eremin.api.client;

import com.eremin.grpctest.Clothes;

public interface ClothesGrpcClient {

    Clothes.ClothesDto save(Clothes.CreateClothesRequest request);

    Clothes.ClothesDto findById(Clothes.FindByIdClothesRequest request);

    Clothes.SearchClothesResponse searchClothes(Clothes.SearchClothesRequest request);
}
