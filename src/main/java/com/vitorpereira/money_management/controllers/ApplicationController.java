package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.dtos.AccountDto;
import com.vitorpereira.money_management.entities.dtos.AccountEditDto;
import com.vitorpereira.money_management.entities.dtos.TransactionDto;
import com.vitorpereira.money_management.entities.dtos.TransactionEditDto;
import com.vitorpereira.money_management.entities.enums.TransactionType;
import com.vitorpereira.money_management.services.ApplicationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/app")
public class ApplicationController {

    private ApplicationServices service;

    public ApplicationController(ApplicationServices service) {
        this.service = service;
    }

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Integer id){
        List<Account> accounts = service.getAccountByUserId(id);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping(value = "/account-create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto dto){
        Account account = service.createAccount(dto);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/account-edit-name/{id}")
    public ResponseEntity<Account> editAccountName(@RequestBody AccountEditDto dto, @PathVariable Integer id){
        Account newAccount = service.editAccountName(dto.getName(), id);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping(value = "/transactions/user-id/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Integer id){
        List<Transaction> transactions = service.getTransactionByUserId(id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping(value = "/transactions/account-id/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Integer id){
        List<Transaction> transactions = service.getTransactionsByAccountId(id);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping(value = "/transaction-create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto dto){
        Transaction transaction = service.createTransaction(dto);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping(value = "/transaction-edit/{id}")
    public ResponseEntity<Transaction> editTransaction(@RequestBody TransactionEditDto dto, @PathVariable Integer id){
        Transaction transaction = service.editTransaction(dto, id);
        return ResponseEntity.ok(transaction);
    }
}
