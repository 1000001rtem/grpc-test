package com.eremin.grpctest.business.service;

import com.eremin.grpctest.business.dto.ClothesDto;
import com.eremin.grpctest.business.dto.CreateClothesRequest;
import com.eremin.grpctest.business.dto.SearchClothesRequest;
import com.eremin.grpctest.business.exception.ClothesNotFoundException;
import com.eremin.grpctest.business.mapper.ClothesMapper;
import com.eremin.grpctest.output.db.model.Clothes;
import com.eremin.grpctest.output.db.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.eremin.grpctest.output.db.specification.ClothesSpecification.equalByBrand;
import static com.eremin.grpctest.output.db.specification.ClothesSpecification.equalByCategory;
import static com.eremin.grpctest.output.db.specification.ClothesSpecification.equalByColor;
import static com.eremin.grpctest.output.db.specification.ClothesSpecification.equalByGender;
import static com.eremin.grpctest.output.db.specification.ClothesSpecification.equalBySeason;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;

    @Transactional
    public ClothesDto save(CreateClothesRequest request) {
        var clothes = clothesRepository.save(
                new Clothes()
                        .setBrand(request.brand())
                        .setName(request.name())
                        .setColor(request.color())
                        .setCategory(request.category())
                        .setSeason(request.season())
                        .setGender(request.gender())
        );
        return ClothesMapper.toDto(clothes);
    }

    @Transactional(readOnly = true)
    public ClothesDto findById(UUID id) {
        return clothesRepository.findById(id).map(ClothesMapper::toDto)
                .orElseThrow(() -> new ClothesNotFoundException("Clothes with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public Page<ClothesDto> search(SearchClothesRequest request, Pageable pageable) {
        return clothesRepository.findAll(
                equalByBrand(request.brand())
                        .and(equalByCategory(request.category()))
                        .and(equalByColor(request.color()))
                        .and(equalByGender(request.gender()))
                        .and(equalBySeason(request.season())),
                pageable
        ).map(ClothesMapper::toDto);
    }

}
