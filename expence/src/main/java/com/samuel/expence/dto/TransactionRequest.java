package com.samuel.expence.dto;
import com.samuel.expence.entity.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private TransactionType type;
    private UUID categoryId;
}
