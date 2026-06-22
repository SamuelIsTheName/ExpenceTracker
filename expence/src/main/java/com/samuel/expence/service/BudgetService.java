package com.samuel.expence.service;

import com.samuel.expence.dto.BudgetRequest;
import com.samuel.expence.dto.BudgetResponse;
import com.samuel.expence.dto.CategoryResponse;
import com.samuel.expence.entity.Budget;
import com.samuel.expence.entity.Category;
import com.samuel.expence.repository.BudgetRepository;
import com.samuel.expence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public List<BudgetResponse>getAllBudgets(){
        return budgetRepository.findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    public BudgetResponse createBudget(BudgetRequest request){
        Category newCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        Budget newBudget = budgetRepository.save(
                new Budget(
                        request.getLimitAmount(),
                        request.getMonth(),
                        request.getYear(),
                        newCategory
                )
        );
        return mapResponse(newBudget);
    }

    private BudgetResponse mapResponse(Budget budget){
        return new BudgetResponse(
                    budget.getId(),
                    budget.getLimitAmount(),
                    budget.getMonth(),
                    budget.getYear(),
                    new CategoryResponse(
                            budget.getCategory().getId(),
                            budget.getCategory().getName()
                    )
        );
    }
}
