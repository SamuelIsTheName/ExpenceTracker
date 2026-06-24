package com.samuel.expence.repository;

import com.samuel.expence.entity.Transaction;
import com.samuel.expence.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByCategory(String categoryName);

    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transaction t
    WHERE t.category.id = :categoryId
    AND t.type = 'EXPENSE'
    AND t.date >= :startDate
    AND t.date < :endDate
    """)
    BigDecimal getTotalSpentForCategoryAndMonth(
            @Param("categoryId") UUID categoryId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
