package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.exception.ProductNotFoundException;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;
import com.cosmocats.intergalacticmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductPage getAll(int page, int size) {
        List<Product> allProducts = repository.findAll();

        int start = Math.min(page * size, allProducts.size());
        int end = Math.min(start + size, allProducts.size());

        return new ProductPage(allProducts.subList(start, end), allProducts.size());
    }

    public Product getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(Product product) {
        return repository.save(withId(product, UUID.randomUUID()));
    }

    public Product update(UUID id, Product product) {
        return repository.update(id, withId(product, id))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private Product withId(Product product, UUID id) {
        return new Product(id, product.getName(), product.getPrice(), product.getCategory());
    }
}
