package com.cosmocats.intergalacticmarket.service.impl;

import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.repository.CategoryRepository;
import com.cosmocats.intergalacticmarket.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
