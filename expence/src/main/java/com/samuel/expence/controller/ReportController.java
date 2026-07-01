package com.samuel.expence.controller;

import com.samuel.expence.dto.CategorySpendingResponse;
import com.samuel.expence.dto.MonthlySpendingResponse;
import com.samuel.expence.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/categories")
    public List<CategorySpendingResponse>getCategoryReportAll
            (@RequestParam(required = false) Integer month,
             @RequestParam(required = false) Integer year)
    {

        if(month != null && year != null){
            return reportService.getSpendingByCategory(month, year);
        }

        return reportService.getSpendingByCategory();
    }

    @GetMapping("/monthly-spending")
    public MonthlySpendingResponse monthlySpendingReport
            (@RequestParam(required = false) Integer month,
             @RequestParam(required = false) Integer year)
    {
        return reportService.getMonthlySpendingReport(month,year);
    }
}
