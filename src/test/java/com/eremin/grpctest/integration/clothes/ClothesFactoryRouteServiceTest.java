package com.eremin.grpctest.integration.clothes;

import com.eremin.grpctest.Clothes;
import com.eremin.grpctest.ClothesRoutesGrpc;
import com.eremin.grpctest.config.IntegrationTest;
import com.eremin.grpctest.output.db.repository.ClothesRepository;
import data.ClothesFactory;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
public class ClothesFactoryRouteServiceTest {

    @Autowired
    private ClothesRoutesGrpc.ClothesRoutesBlockingStub client;

    @Autowired
    private ClothesRepository clothesRepository;

    @Test
    public void shouldCreateClothes() {
        var request = Clothes.CreateClothesRequest.newBuilder()
                .setName("Test")
                .setColor("Test")
                .setGender("Test")
                .setCategory("Test")
                .build();

        var result = client.save(request);
        assertEquals("Test", result.getName());
        assertEquals("Test", result.getColor());
        assertEquals("Test", result.getGender());
        assertEquals("Test", result.getCategory());

        assertEquals(1, clothesRepository.count());
    }

    @Test
    public void shouldFindClothesById() {
        var clothes = clothesRepository.save(ClothesFactory.createClothes(UUID.randomUUID(), null, null, null, null, null));
        var request = Clothes.FindByIdClothesRequest.newBuilder().setId(clothes.getId().toString()).build();

        var result = client.findById(request);

        assertNotNull(result);
        assertEquals(clothes.getId().toString(), result.getId());
    }

    @Test
    public void shouldReturnNotFoundWhenClothesDoesNotExist() {
        var request = Clothes.FindByIdClothesRequest.newBuilder().setId(UUID.randomUUID().toString()).build();

        var e = assertThrows(StatusRuntimeException.class, () -> client.findById(request));

        assertEquals(Status.NOT_FOUND.getCode(), e.getStatus().getCode());
    }

    @Test
    public void shouldSearchClothes() {
        var clothes = List.of(
                ClothesFactory.createClothes(UUID.randomUUID(), null, "testBrand", "testColor", null, null),
                ClothesFactory.createClothes(UUID.randomUUID(), null, "testBrand", "testColor", null, null),
                ClothesFactory.createClothes(UUID.randomUUID(), null, "testBrand", "testColor", null, null),
                ClothesFactory.createClothes(UUID.randomUUID(), null, "testBrand1", null, null, null),
                ClothesFactory.createClothes(UUID.randomUUID(), null, null, null, null, null)
        );

        clothesRepository.saveAll(clothes);

        var request = Clothes.SearchClothesRequest.newBuilder().setBrand("testBrand").build();
        var result = client.searchClothes(request);
        assertEquals(3, result.getContentList().size());

        request = Clothes.SearchClothesRequest.newBuilder().setBrand("testBrand").setColor("testColor").build();
        result = client.searchClothes(request);
        assertEquals(3, result.getContentList().size());

        request = Clothes.SearchClothesRequest.newBuilder().setBrand("testBrand1").build();
        result = client.searchClothes(request);
        assertEquals(1, result.getContentList().size());
    }
}
