package com.samuel.expence.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class CategoryRequest {

    //@NotBlank(message = "This section can not be empty")
    private String name;
}
