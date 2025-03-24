package com.eremin.grpctest.output.db.specification;

import com.eremin.grpctest.output.db.model.Clothes;
import com.eremin.grpctest.output.db.model.Clothes_;
import org.springframework.data.jpa.domain.Specification;

public class ClothesSpecification {

    public static Specification<Clothes> equalByGender(String gender) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (gender == null) return null;
            return criteriaBuilder.equal(root.get(Clothes_.gender), gender);
        };
    }

    public static Specification<Clothes> equalByCategory(String category) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (category == null) return null;
            return criteriaBuilder.equal(root.get(Clothes_.category), category);
        };
    }

    public static Specification<Clothes> equalBySeason(String season) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (season == null) return null;
            return criteriaBuilder.equal(root.get(Clothes_.season), season);
        };
    }

    public static Specification<Clothes> equalByColor(String color) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (color == null) return null;
            return criteriaBuilder.equal(root.get(Clothes_.color), color);
        };
    }

    public static Specification<Clothes> equalByBrand(String brand) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (brand == null) return null;
            return criteriaBuilder.equal(root.get(Clothes_.brand), brand);
        };
    }
}
