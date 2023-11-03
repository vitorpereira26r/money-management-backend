package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.UserApplication;

public class AccountDto {

    private String name;
    private Integer userId;

    public AccountDto() {
    }

    public AccountDto(Account account){
        this.name = account.getName();
        this.userId = account.getUser().getId();
    }

    public AccountDto(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
