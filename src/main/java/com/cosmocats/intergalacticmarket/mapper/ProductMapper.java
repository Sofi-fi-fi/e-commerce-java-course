package com.cosmocats.intergalacticmarket.mapper;

import com.cosmocats.intergalacticmarket.dto.ProductCreateDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toDto(Product product) {
        if (product == null) return null;
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryName
        );
    }

    public Product toDomain(ProductCreateDto dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        if (dto.getCategoryName() != null) {
            product.setCategory(new Category(null, dto.getCategoryName()));
        }
        return product;
    }
}
