package com.cosmocats.intergalacticmarket.repository;

import com.cosmocats.intergalacticmarket.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    List<Category> findAll();

    Optional<Category> findById(UUID id);
}
