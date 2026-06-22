package com.samuel.expence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "budget")
public class Budget {

    public Budget(BigDecimal limitAmount,Integer month,Integer year,Category category)
    {
        this.limitAmount = limitAmount;
        this.month = month;
        this.year = year;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private BigDecimal limitAmount;
    private Integer month;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
