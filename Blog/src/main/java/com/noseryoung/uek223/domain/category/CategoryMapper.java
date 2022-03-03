package com.noseryoung.uek223.domain.category;

import com.noseryoung.uek223.domain.category.dto.CategoryDTOOnlyName;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryDTOOnlyName> categoryToCategoryDTOOnlyName(List<Category> categories);
}
