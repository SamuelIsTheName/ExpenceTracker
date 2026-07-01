package com.samuel.expence.dto;

import java.math.BigDecimal;

public record BudgetSummaryResponse(
        BigDecimal limitAmount,
        BigDecimal amountSpent,
        BigDecimal amountLeft
) {
}
