package com.cosmocats.intergalacticmarket.mapper;

import com.cosmocats.intergalacticmarket.dto.ProductCreateDto;
import com.cosmocats.intergalacticmarket.dto.ProductResponseDto;
import com.cosmocats.intergalacticmarket.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapStructMapper {

    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category.name", source = "categoryName")
    Product toDomain(ProductCreateDto dto);
}
