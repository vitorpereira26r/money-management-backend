package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.Category;
import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.AccountDto;
import com.vitorpereira.money_management.entities.dtos.AccountEditDto;
import com.vitorpereira.money_management.entities.dtos.TransactionDto;
import com.vitorpereira.money_management.entities.dtos.TransactionEditDto;
import com.vitorpereira.money_management.entities.enums.TransactionType;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.services.ApplicationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/app")
public class ApplicationController {

    private ApplicationServices service;

    public ApplicationController(ApplicationServices service) {
        this.service = service;
    }

    private String getUsernameFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        throw new ResourceNotFoundException("Username could not be retrieved from the token.");
    }

    @GetMapping(value = "/account")
    public ResponseEntity<List<Account>> getAccounts(){
        String username = getUsernameFromToken();
        List<Account> accounts = service.getAccountByUser(username);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping(value = "/account")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto dto){
        Account account = service.createAccount(dto);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/account/{id}")
    public ResponseEntity<Account> editAccountName(@RequestBody AccountEditDto dto, @PathVariable Integer id){
        Account newAccount = service.editAccountName(dto.getName(), id);
        return ResponseEntity.ok(newAccount);
    }

    @DeleteMapping(value = "/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/transaction")
    public ResponseEntity<List<Transaction>> getTransactionsByUser(){
        String username = getUsernameFromToken();
        List<Transaction> transactions = service.getTransactionByUser(username);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Integer id){
        List<Transaction> transactions = service.getTransactionsByAccountId(id);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping(value = "/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto dto){
        Transaction transaction = service.createTransaction(dto);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping(value = "/transaction/{id}")
    public ResponseEntity<Transaction> editTransaction(@RequestBody TransactionEditDto dto, @PathVariable Integer id){
        Transaction transaction = service.editTransaction(dto, id);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping(value = "/transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id){
        service.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = service.getCategories();
        return ResponseEntity.ok(categories);
    }
}
