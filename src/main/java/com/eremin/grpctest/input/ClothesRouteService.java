package com.eremin.grpctest.input;

import com.eremin.grpctest.Clothes;
import com.eremin.grpctest.ClothesRoutesGrpc;
import com.eremin.grpctest.business.dto.ClothesDto;
import com.eremin.grpctest.business.service.ClothesService;
import com.eremin.grpctest.input.mapper.ClothesMapper;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ClothesRouteService extends ClothesRoutesGrpc.ClothesRoutesImplBase {

    private final ClothesService clothesService;

    private static Status NOT_FOUND_STATUS(String id) {
        return com.google.rpc.Status.newBuilder()
                .setCode(Code.NOT_FOUND.getNumber())
                .setMessage("Clothes with id: " + id + "Not Found")
                .build();
    }

    @Override
    public void save(Clothes.CreateClothesRequest request, StreamObserver<Clothes.ClothesDto> responseObserver) {
        ClothesDto clothes = clothesService.save(ClothesMapper.toDto(request));
        Clothes.ClothesDto response = ClothesMapper.toResponse(clothes);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(Clothes.FindByIdClothesRequest request, StreamObserver<Clothes.ClothesDto> responseObserver) {
        ClothesDto clothes = clothesService.findById(UUID.fromString(request.getId()));

        Clothes.ClothesDto response = ClothesMapper.toResponse(clothes);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void searchClothes(Clothes.SearchClothesRequest request, StreamObserver<Clothes.SearchClothesResponse> responseObserver) {
        var result = clothesService.search(ClothesMapper.toSearchDto(request), PageRequest.of(0, 10));

        var content = result.getContent().stream().map(ClothesMapper::toResponse).toList();

        var response = Clothes.SearchClothesResponse.newBuilder()
                .addAllContent(content)
                .setPage(result.getNumber())
                .setTotal(result.getTotalElements())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
