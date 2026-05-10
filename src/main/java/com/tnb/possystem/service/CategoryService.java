package com.tnb.possystem.service;

import com.tnb.possystem.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
