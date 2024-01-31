package com.kwizera.ibrahim.adminPanel.service.impl;

import com.kwizera.ibrahim.adminPanel.dto.Mapper;
import com.kwizera.ibrahim.adminPanel.dto.requestDto.CategoryRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.CategoryResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Category;
import com.kwizera.ibrahim.adminPanel.repository.CategoryRepository;
import com.kwizera.ibrahim.adminPanel.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;

    //@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {

        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        categoryRepository.save(category);
        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.categoryToCategoryResponseDtos(categories);
    }

    @NonNull
    @Override
    public Category getCategory(@NonNull Long categoryID) {
        return categoryRepository.findById(categoryID).orElseThrow(()->
                new IllegalArgumentException(
                        "Could not find category with id:" +categoryID
                )
        );
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryID) {
        Category category = getCategory(categoryID);
        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional
    @Override
    public CategoryResponseDto editCategory(Long categoryID, CategoryRequestDto categoryRequestDto) {
        Category category = getCategory(categoryID);
        category.setName(categoryRequestDto.getName());
        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto deleteCategory(Long categoryID) {
        Category category = getCategory(categoryID);
        categoryRepository.delete(category);
        return Mapper.categoryToCategoryResponseDto(category);
    }

}
