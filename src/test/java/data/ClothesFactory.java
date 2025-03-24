package data;

import java.util.UUID;

public class ClothesFactory {

    public static com.eremin.grpctest.output.db.model.Clothes createClothes(
            UUID id,
            String name,
            String brand,
            String color,
            String gender,
            String category
    ) {
        return new com.eremin.grpctest.output.db.model.Clothes()
                .setId(id == null ? UUID.randomUUID() : id)
                .setName(name == null ? "Test" : name)
                .setBrand(brand == null ? "Test" : brand)
                .setColor(color == null ? "Test" : color)
                .setGender(gender == null ? "Test" : gender)
                .setCategory(category == null ? "Test" : category);
    }
}
