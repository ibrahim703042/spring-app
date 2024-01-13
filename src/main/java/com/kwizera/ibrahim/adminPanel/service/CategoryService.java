package com.kwizera.ibrahim.adminPanel.service;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.CategoryRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.requestDto.ZipcodeRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.CategoryResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Category;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public List<CategoryResponseDto> getCategories();
    public Category getCategory(Long categoryID);
    public CategoryResponseDto getCategoryById(Long categoryID);
    public CategoryResponseDto editCategory(Long categoryID, CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto deleteCategory(Long categoryID);

}
