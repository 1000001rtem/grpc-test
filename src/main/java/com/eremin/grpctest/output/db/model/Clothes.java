package com.eremin.grpctest.output.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Clothes {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String gender;
    private String category;
    private String season;
    private String brand;
    private String color;
}
