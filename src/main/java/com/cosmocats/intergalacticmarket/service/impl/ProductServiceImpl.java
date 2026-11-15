package com.cosmocats.intergalacticmarket.service.impl;

import com.cosmocats.intergalacticmarket.exception.CategoryNotFoundException;
import com.cosmocats.intergalacticmarket.exception.ProductNotFoundException;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;
import com.cosmocats.intergalacticmarket.repository.CategoryRepository;
import com.cosmocats.intergalacticmarket.repository.ProductRepository;
import com.cosmocats.intergalacticmarket.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductPage getAllProducts(int page, int size) {
        List<Product> allProducts = productRepository.findAll().stream()
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

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(Product product, UUID categoryId) {
        Category category = findCategory(categoryId);
        return productRepository.save(withIdAndCategory(product, UUID.randomUUID(), category));
    }

    @Override
    public Product updateProduct(UUID id, Product product, UUID categoryId) {
        Category category = findCategory(categoryId);
        return productRepository.update(id, withIdAndCategory(product, id, category))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }

    private Category findCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private Product withIdAndCategory(Product product, UUID id, Category category) {
        return new Product(id, product.getName(), product.getPrice(), category);
    }
}
