package com.cosmocats.intergalacticmarket.repository;

import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public InMemoryProductRepository() {
        save(new Product(null, "Cosmic Star Treats", new BigDecimal("15.99"), new Category(1L, "Food")));
        save(new Product(null, "Galaxy Laser Toy", new BigDecimal("29.99"), new Category(1L, "Toys")));
        save(new Product(null, "Comet Scratching Post", new BigDecimal("49.99"), new Category(1L, "Furniture")));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Product save(Product product) {
        product.setId(idGenerator.getAndIncrement());
        storage.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        return Optional.ofNullable(storage.computeIfPresent(id, (key, existing) -> {
            product.setId(key);
            return product;
        }));
    }

    @Override
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
