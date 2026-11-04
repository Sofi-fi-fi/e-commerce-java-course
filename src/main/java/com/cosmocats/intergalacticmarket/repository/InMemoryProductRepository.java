package com.cosmocats.intergalacticmarket.repository;

import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> storage = new ConcurrentHashMap<>();

    public InMemoryProductRepository(CategoryRepository categoryRepository) {
        save(new Product(UUID.fromString("7c9e6679-7425-40de-944b-e07fc1f90ae7"),
                "Cosmic Star Treats", new BigDecimal("15.99"),
                category(categoryRepository, InMemoryCategoryRepository.FOOD_ID)));
        save(new Product(UUID.fromString("16fd2706-8baf-433b-82eb-8c7fada847da"),
                "Galaxy Laser Toy", new BigDecimal("29.99"),
                category(categoryRepository, InMemoryCategoryRepository.TOYS_ID)));
        save(new Product(UUID.fromString("886313e1-3b8a-4372-9b90-0c9aee199e5d"),
                "Comet Scratching Post", new BigDecimal("49.99"),
                category(categoryRepository, InMemoryCategoryRepository.FURNITURE_ID)));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Product save(Product product) {
        storage.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> update(UUID id, Product product) {
        return Optional.ofNullable(storage.computeIfPresent(id, (key, existing) -> product));
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }

    private static Category category(CategoryRepository categoryRepository, UUID id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
