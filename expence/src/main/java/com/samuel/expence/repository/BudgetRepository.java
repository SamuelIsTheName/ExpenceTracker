package com.samuel.expence.repository;

import com.samuel.expence.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
    Optional<Budget> findByCategoryIdAndMonthAndYear(
            UUID categoryId,
            Integer month,
            Integer year
    );
}
