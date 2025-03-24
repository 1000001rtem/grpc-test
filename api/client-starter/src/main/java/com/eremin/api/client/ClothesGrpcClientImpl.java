package com.eremin.api.client;

import com.eremin.grpctest.Clothes;
import com.eremin.grpctest.ClothesRoutesGrpc;

public class ClothesGrpcClientImpl implements ClothesGrpcClient {

    private final ClothesRoutesGrpc.ClothesRoutesBlockingStub stub;

    public ClothesGrpcClientImpl(ClothesRoutesGrpc.ClothesRoutesBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public Clothes.ClothesDto save(Clothes.CreateClothesRequest request) {
        return stub.save(request);
    }

    @Override
    public Clothes.ClothesDto findById(Clothes.FindByIdClothesRequest request) {
        return stub.findById(request);
    }

    @Override
    public Clothes.SearchClothesResponse searchClothes(Clothes.SearchClothesRequest request) {
        return stub.searchClothes(request);
    }
}
