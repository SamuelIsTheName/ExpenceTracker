package com.samuel.expence.controller;

import com.samuel.expence.dto.BudgetRequest;
import com.samuel.expence.dto.BudgetResponse;
import com.samuel.expence.dto.BudgetSummaryResponse;
import com.samuel.expence.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public BudgetResponse createBudget(@RequestBody BudgetRequest request){
        return budgetService.createBudget(request);
    }

    @GetMapping("/{id}")
    public BudgetResponse getSpecificBudget(@PathVariable UUID id){

        return budgetService.getBudgetById(id);
    }

    @GetMapping
    public List<BudgetResponse> getBudgets(){
        // contains all the functions that bring more than one budget

        return budgetService.getAllBudgets();
    }

    @GetMapping("/summary")
    public BudgetSummaryResponse getSummary(
            @RequestParam UUID categoryId,
            @RequestParam Integer month,
            @RequestParam Integer year
    ) {
        return budgetService.getBudgetSummary(
                categoryId,
                month,
                year
        );
    }

    @PutMapping("/id")
    public BudgetResponse updateBudget(@PathVariable UUID id, @RequestBody BudgetRequest request){
        return budgetService.updateBudget(id, request);
    }
}
