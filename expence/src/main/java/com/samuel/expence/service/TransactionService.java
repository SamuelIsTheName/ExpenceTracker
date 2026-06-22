package com.samuel.expence.service;

import com.samuel.expence.dto.CategoryResponse;
import com.samuel.expence.dto.TransactionRequest;
import com.samuel.expence.dto.TransactionResponse;
import com.samuel.expence.entity.Category;
import com.samuel.expence.entity.Transaction;
import com.samuel.expence.repository.CategoryRepository;
import com.samuel.expence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public List<TransactionResponse> getAllTransaction(){
        return transactionRepository.findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    public TransactionResponse getTransactionById(UUID transactionId){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(()->new RuntimeException("Transaction not found"));

        return mapResponse(transaction);
    }

    public TransactionResponse createTransaction(TransactionRequest transactionDTO){
        Category newCategory = categoryRepository.findById(transactionDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Transaction not found"));

        Transaction newTransaction = transactionRepository.save(new Transaction(transactionDTO.getAmount(), transactionDTO.getDescription(), transactionDTO.getDate(), transactionDTO.getType(), newCategory) );

        return mapResponse(newTransaction);
    }

    private TransactionResponse mapResponse(Transaction transaction){
        return new TransactionResponse(
                                transaction.getId(),
                                transaction.getAmount(),
                                transaction.getDescription(),
                                transaction.getDate(),
                                transaction.getType(),
                                new CategoryResponse(
                                        transaction.getCategory().getId(),
                                        transaction.getCategory().getName()
                                )
                        );
    }
}
