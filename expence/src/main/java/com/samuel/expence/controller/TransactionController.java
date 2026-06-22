package com.samuel.expence.controller;

import com.samuel.expence.dto.TransactionRequest;
import com.samuel.expence.dto.TransactionResponse;
import com.samuel.expence.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request){
        return transactionService.createTransaction(request);
    }
}
