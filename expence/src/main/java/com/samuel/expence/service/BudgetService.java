package com.samuel.expence.service;

import com.samuel.expence.dto.*;
import com.samuel.expence.entity.Budget;
import com.samuel.expence.entity.Category;
import com.samuel.expence.repository.BudgetRepository;
import com.samuel.expence.repository.CategoryRepository;
import com.samuel.expence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    private final TransactionRepository transactionRepository;

    public List<BudgetResponse>getAllBudgets(){
        return budgetRepository.findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    public BudgetResponse createBudget(BudgetRequest request){
        if (budgetRepository.findByCategoryIdAndMonthAndYear(request.getCategoryId(),request.getMonth(), request.getYear()).isPresent()){throw new RuntimeException("Budget already exists"); }

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

    public BudgetResponse getBudgetById(UUID budgetId){
        Budget foundBudget = budgetRepository.findById(budgetId).orElseThrow(()->new RuntimeException("Id not valid"));
        return mapResponse(foundBudget);
    }

    public BudgetResponse updateBudget(UUID budgetId, BudgetRequest request){
        Budget foundBudget = budgetRepository.findById(budgetId).orElseThrow(()->new RuntimeException("Id not valid"));
        Category newCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));

        foundBudget.setLimitAmount(request.getLimitAmount());
        foundBudget.setMonth(request.getMonth());
        foundBudget.setYear(request.getYear());
        foundBudget.setCategory(newCategory);

        Budget updatedBudget = budgetRepository.save(foundBudget);
        return mapResponse(updatedBudget);
    }

    public BudgetSummaryResponse getBudgetSummary(UUID categoryId, Integer month, Integer year){

        Budget budget = budgetRepository.findByCategoryIdAndMonthAndYear(categoryId,month,year).orElseThrow(()-> new RuntimeException("Budget does not exist"));

        LocalDateTime startDate = LocalDate.of(budget.getYear(), budget.getMonth(), 1).atStartOfDay();
        LocalDateTime endDate = startDate.plusMonths(1);

        BigDecimal amountSpent =transactionRepository.getTotalSpentForCategoryAndMonth(budget.getCategory().getId(), startDate, endDate);
        BigDecimal amountLeft = budget.getLimitAmount().subtract(amountSpent);

        //TODO add monthly savings as-well by subtracting total monthly expenses from monthly income.

        return new BudgetSummaryResponse(budget.getLimitAmount(),amountSpent,amountLeft);

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
