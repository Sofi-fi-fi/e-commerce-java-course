package com.cosmocats.intergalacticmarket.service.impl;

import com.cosmocats.intergalacticmarket.exception.CategoryNotFoundException;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.repository.CategoryRepository;
import com.cosmocats.intergalacticmarket.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
