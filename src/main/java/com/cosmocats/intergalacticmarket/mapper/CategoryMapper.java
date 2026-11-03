package com.cosmocats.intergalacticmarket.mapper;

import com.cosmocats.intergalacticmarket.dto.CategoryResponseDto;
import com.cosmocats.intergalacticmarket.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);
}
