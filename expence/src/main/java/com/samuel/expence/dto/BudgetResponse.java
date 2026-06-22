package com.samuel.expence.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BudgetResponse {

    private UUID id;
    private BigDecimal limitAmount;
    private Integer month;
    private Integer year;
    private CategoryResponse category;
}
