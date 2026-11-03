package com.cosmocats.intergalacticmarket.repository;

import com.cosmocats.intergalacticmarket.model.Category;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {

    public static final UUID FOOD_ID = UUID.fromString("3f8c1a52-6b1e-4c7a-9d2e-5a4b7c8d9e01");
    public static final UUID TOYS_ID = UUID.fromString("a1d4e6f8-2b3c-4d5e-8f90-1a2b3c4d5e02");
    public static final UUID FURNITURE_ID = UUID.fromString("c7e9f1a3-4b5d-4e6f-9a0b-2c3d4e5f6a03");

    private final Map<UUID, Category> categories = new LinkedHashMap<>();

    public InMemoryCategoryRepository() {
        add(new Category(FOOD_ID, "Food"));
        add(new Category(TOYS_ID, "Toys"));
        add(new Category(FURNITURE_ID, "Furniture"));
    }

    @Override
    public List<Category> findAll() {
        return List.copyOf(categories.values());
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return Optional.ofNullable(categories.get(id));
    }

    private void add(Category category) {
        categories.put(category.getId(), category);
    }
}
