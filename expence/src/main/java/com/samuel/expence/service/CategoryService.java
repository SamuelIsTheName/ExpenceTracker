package com.samuel.expence.service;

import com.samuel.expence.dto.CategoryRequest;
import com.samuel.expence.dto.CategoryResponse;
import com.samuel.expence.entity.Category;
import com.samuel.expence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request){
        Category newCategory = new Category();
        newCategory.setName(request.getName());

        Category savedCategory = categoryRepository.save(newCategory);

        return new CategoryResponse(
                newCategory.getId(), newCategory.getName()
        );
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName()
                )).toList();
    }
}
