package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.UserApplication;

public class AccountDto {

    private String name;

    public AccountDto() {
    }

    public AccountDto(Account account){
        this.name = account.getName();
    }

    public AccountDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
