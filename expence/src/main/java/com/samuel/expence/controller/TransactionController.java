package com.samuel.expence.controller;

import com.samuel.expence.dto.TransactionRequest;
import com.samuel.expence.dto.TransactionResponse;
import com.samuel.expence.entity.TransactionType;
import com.samuel.expence.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request){
        return transactionService.createTransaction(request);
    }

    @GetMapping()
    public List<TransactionResponse> getTransactions(@RequestParam(required = false) TransactionType type, @RequestParam(required = false) String category){
        if(type != null)
        {
            return transactionService.getTransactionByType(type);
        }
        if(category != null)
        {
            return transactionService.getTransactionByCategory(category);
        }
        return transactionService.getAllTransaction();
    }
}
