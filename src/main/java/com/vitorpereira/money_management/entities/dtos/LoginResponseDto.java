package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.UserApplication;

public class LoginResponseDto {

    private UserApplication user;
    private String jwt;

    public LoginResponseDto() {
    }

    public LoginResponseDto(UserApplication user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public UserApplication getUser() {
        return user;
    }

    public void setUser(UserApplication user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
