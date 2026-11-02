package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.dto.PageResponseDto;
import com.cosmocats.intergalacticmarket.dto.ProductRequestDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.exception.ProductNotFoundException;
import com.cosmocats.intergalacticmarket.mapper.ProductMapper;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PageResponseDto<ProductResponseDto> getAll(int page, int size) {
        List<ProductResponseDto> allProducts = repository.findAll().stream()
                .map(mapper::toDto)
                .toList();

        int start = Math.min(page * size, allProducts.size());
        int end = Math.min(start + size, allProducts.size());

        List<ProductResponseDto> pagedContent = allProducts.subList(start, end);

        return PageResponseDto.of(pagedContent, page, size, allProducts.size());
    }

    public ProductResponseDto getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return mapper.toDto(product);
    }

    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = repository.save(mapper.toDomain(dto));
        return mapper.toDto(product);
    }

    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = repository.update(id, mapper.toDomain(dto))
                .orElseThrow(() -> new ProductNotFoundException(id));
        return mapper.toDto(product);
    }

    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new ProductNotFoundException(id);
        }
    }
}
