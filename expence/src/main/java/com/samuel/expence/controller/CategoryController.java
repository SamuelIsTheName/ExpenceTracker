package com.samuel.expence.controller;

import com.samuel.expence.dto.CategoryRequest;
import com.samuel.expence.dto.CategoryResponse;
import com.samuel.expence.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
//@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
