package com.samuel.expence.dto;
import com.samuel.expence.entity.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class TransactionResponse {
    private UUID id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private TransactionType type;

    private CategoryResponse category;
}
