package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(UUID id);
}
