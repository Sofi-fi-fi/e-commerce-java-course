package com.cosmocats.intergalacticmarket.controller;

import com.cosmocats.intergalacticmarket.dto.CategoryResponseDto;
import com.cosmocats.intergalacticmarket.mapper.CategoryMapper;
import com.cosmocats.intergalacticmarket.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories().stream()
                .map(categoryMapper::toCategoryDto)
                .toList());
    }
}
