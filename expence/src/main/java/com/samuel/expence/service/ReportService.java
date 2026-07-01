package com.samuel.expence.service;

import com.samuel.expence.dto.CategorySpendingResponse;
import com.samuel.expence.dto.MonthlySpendingResponse;
import com.samuel.expence.entity.TransactionType;
import com.samuel.expence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransactionRepository transactionRepository;

    public List<CategorySpendingResponse>getSpendingByCategory(){

        return transactionRepository.getAllTimeSpendingByCategory();
    }

    public List<CategorySpendingResponse>getSpendingByCategory(Integer month, Integer year){
        LocalDateTime startDate = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endDate = startDate.plusMonths(1);

        return transactionRepository.getMonthlySpendingByCategory(startDate, endDate);
    }

    public MonthlySpendingResponse getMonthlySpendingReport(Integer month, Integer year){
        LocalDateTime startDate = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endDate = startDate.plusMonths(1);

        BigDecimal expense = transactionRepository.getTotalByType(TransactionType.EXPENSE, startDate, endDate);
        BigDecimal income = transactionRepository.getTotalByType(TransactionType.INCOME, startDate, endDate);

        BigDecimal savings = income.subtract(expense);

        return new MonthlySpendingResponse(month,income, expense, savings);
    }
}
