package com.vitorpereira.money_management.entities.dtos;

public class AccountEditDto {

    private String name;

    public AccountEditDto() {
    }

    public AccountEditDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
