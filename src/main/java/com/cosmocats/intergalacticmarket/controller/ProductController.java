package com.cosmocats.intergalacticmarket.controller;

import com.cosmocats.intergalacticmarket.dto.PageResponseDto;
import com.cosmocats.intergalacticmarket.dto.ProductRequestDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.mapper.ProductMapper;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;
import com.cosmocats.intergalacticmarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<PageResponseDto<ProductResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ProductPage productPage = productService.getAll(page, size);
        List<ProductResponseDto> content = productPage.items().stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(PageResponseDto.of(content, page, size, productPage.totalElements()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(productService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {
        Product created = productService.create(mapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto dto) {
        Product updated = productService.update(id, mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
