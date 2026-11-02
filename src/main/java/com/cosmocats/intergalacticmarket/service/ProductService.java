package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.exception.ProductNotFoundException;
import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;
import com.cosmocats.intergalacticmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        return repository.update(id, product)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new ProductNotFoundException(id);
        }
    }
}
