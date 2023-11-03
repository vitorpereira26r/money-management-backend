package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServices {

    private AccountService accountService;

    public ApplicationServices(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<Account> getAccountByUserId(Integer id){
        List<Account> accounts = accountService.getAccountByUserId(id);

        return accounts;
    }
}
