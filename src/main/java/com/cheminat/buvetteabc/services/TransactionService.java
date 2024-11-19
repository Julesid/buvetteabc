package com.cheminat.buvetteabc.services;

import org.springframework.stereotype.Service;

import com.cheminat.buvetteabc.data.Transaction;
import com.cheminat.buvetteabc.data.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

