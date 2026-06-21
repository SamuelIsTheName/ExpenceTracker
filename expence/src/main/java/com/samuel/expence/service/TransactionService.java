package com.samuel.expence.service;

import com.samuel.expence.dto.CategoryResponse;
import com.samuel.expence.dto.TransactionResponse;
import com.samuel.expence.entity.Transaction;
import com.samuel.expence.repository.TransactionRepository;

import java.util.List;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository repository){
        transactionRepository = repository;
    }

    public List<TransactionResponse> getAllTransaction(){
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getDate(),
                        transaction.getType(),
                        new CategoryResponse(
                                transaction.getCategory().getId(),
                                transaction.getCategory().getName()
                        )
                        )
                ).toList();
    }
}
