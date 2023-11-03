package com.vitorpereira.money_management.controllers;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.services.ApplicationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
