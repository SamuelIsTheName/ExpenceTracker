package com.samuel.expence.dto;
import java.math.BigDecimal;
public record CategorySpendingResponse
        (String category, BigDecimal total) {

}
