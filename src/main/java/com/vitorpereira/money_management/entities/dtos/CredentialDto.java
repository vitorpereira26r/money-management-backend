package com.vitorpereira.money_management.entities.dtos;

public class CredentialDto {

    private String username;
    private String password;

    public CredentialDto() {
    }

    public CredentialDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
