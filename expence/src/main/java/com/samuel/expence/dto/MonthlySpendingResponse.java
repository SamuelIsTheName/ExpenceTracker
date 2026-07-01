package com.samuel.expence.dto;

import java.math.BigDecimal;

public record MonthlySpendingResponse
        (Integer month, BigDecimal income, BigDecimal expense, BigDecimal saved)
{
}
