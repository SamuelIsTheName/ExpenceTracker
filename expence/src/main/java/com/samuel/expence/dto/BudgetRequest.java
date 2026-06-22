package com.samuel.expence.dto;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class BudgetRequest {

    private UUID id;
    private BigDecimal limitAmount;
    private Integer month;
    private Integer year;
    private UUID categoryId;
}
