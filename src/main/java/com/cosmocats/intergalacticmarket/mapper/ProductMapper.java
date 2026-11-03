package com.cosmocats.intergalacticmarket.mapper;

import com.cosmocats.intergalacticmarket.dto.ProductRequestDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.model.Category;
import com.cosmocats.intergalacticmarket.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryName")
    Product toDomain(ProductRequestDto dto);

    default Category toCategory(String categoryName) {
        return categoryName == null ? null : new Category(null, categoryName);
    }
}
