package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.UserApplication;

public class LoginResponseDto {

    private String token;
    private UserApplication user;

    public LoginResponseDto() {
    }

    public LoginResponseDto(UserApplication user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserApplication getUser() {
        return user;
    }

    public void setUser(UserApplication user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
