package com.cosmocats.intergalacticmarket.service;

import com.cosmocats.intergalacticmarket.model.Product;
import com.cosmocats.intergalacticmarket.model.ProductPage;

import java.util.UUID;

public interface ProductService {

    ProductPage getAllProducts(int page, int size);

    Product getProductById(UUID id);

    Product createProduct(Product product, UUID categoryId);

    Product updateProduct(UUID id, Product product, UUID categoryId);

    void deleteProductById(UUID id);
}
