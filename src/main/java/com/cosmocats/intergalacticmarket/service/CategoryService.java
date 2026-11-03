package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }
}
