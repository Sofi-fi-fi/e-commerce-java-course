package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.dto.PageResponseDto;
import com.cosmocats.intergalacticmarket.dto.ProductCreateDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.exception.ResourceNotFoundException;
import com.cosmocats.intergalacticmarket.mapper.ProductMapper;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final Map<Long, Product> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ProductMapper mapper;

    public ProductService(ProductMapper mapper) {
        this.mapper = mapper;
        initMockData();
    }

    private void initMockData() {
        createProductInternal("Cosmic Star Treats", new BigDecimal("15.99"), "Food");
        createProductInternal("Galaxy Laser Toy", new BigDecimal("29.99"), "Toys");
        createProductInternal("Comet Scratching Post", new BigDecimal("49.99"), "Furniture");
    }

    private void createProductInternal(String name, BigDecimal price, String categoryName) {
        Long id = idGenerator.getAndIncrement();
        Product product = new Product(id, name, price, new Category(1L, categoryName));
        storage.put(id, product);
    }

    public PageResponseDto<ProductResponseDto> getAll(int page, int size) {
        List<ProductResponseDto> allProducts = storage.values().stream()
                .map(mapper::toDto)
                .toList();

        int start = Math.min(page * size, allProducts.size());
        int end = Math.min(start + size, allProducts.size());

        List<ProductResponseDto> pagedContent = allProducts.subList(start, end);

        return new PageResponseDto<>(pagedContent, page, size, allProducts.size());
    }

    public ProductResponseDto getById(Long id) {
        Product product = Optional.ofNullable(storage.get(id))
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        return mapper.toDto(product);
    }

    public ProductResponseDto create(ProductCreateDto dto) {
        Product product = mapper.toDomain(dto);
        product.setId(idGenerator.getAndIncrement());
        storage.put(product.getId(), product);
        return mapper.toDto(product);
    }

    public ProductResponseDto update(Long id, ProductCreateDto dto) {
        if (!storage.containsKey(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
        Product product = mapper.toDomain(dto);
        product.setId(id);
        storage.put(id, product);
        return mapper.toDto(product);
    }

    public void delete(Long id) {
        if (!storage.containsKey(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
        storage.remove(id);
    }
}

