package com.samuel.expence.repository;

import com.samuel.expence.entity.Transaction;
import com.samuel.expence.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByCategory(String categoryName);
}
