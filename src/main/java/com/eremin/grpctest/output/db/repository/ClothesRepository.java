package com.eremin.grpctest.output.db.repository;

import com.eremin.grpctest.output.db.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClothesRepository extends JpaRepository<Clothes, UUID>, JpaSpecificationExecutor<Clothes> {
}
