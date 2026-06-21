package com.samuel.expence.dto;

import lombok.*;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private UUID id;
    private String name;
}
