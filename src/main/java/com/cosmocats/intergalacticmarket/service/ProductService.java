package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.exception.CategoryNotFoundException;
import com.cosmocats.intergalacticmarket.exception.ProductNotFoundException;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;
import com.cosmocats.intergalacticmarket.repository.CategoryRepository;
import com.cosmocats.intergalacticmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public ProductPage getAll(int page, int size) {
        List<Product> allProducts = repository.findAll().stream()
                .sorted(Comparator.comparing(Product::getId))
                .toList();

        long offset = (long) page * size;
        if (offset >= allProducts.size()) {
            return new ProductPage(List.of(), allProducts.size());
        }

        int start = (int) offset;
        int end = Math.min(start + size, allProducts.size());

        return new ProductPage(allProducts.subList(start, end), allProducts.size());
    }

    public Product getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(Product product, UUID categoryId) {
        Category category = findCategory(categoryId);
        return repository.save(withIdAndCategory(product, UUID.randomUUID(), category));
    }

    public Product update(UUID id, Product product, UUID categoryId) {
        Category category = findCategory(categoryId);
        return repository.update(id, withIdAndCategory(product, id, category))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private Category findCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private Product withIdAndCategory(Product product, UUID id, Category category) {
        return new Product(id, product.getName(), product.getPrice(), category);
    }
}
