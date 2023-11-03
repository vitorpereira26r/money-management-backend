package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.UserApplication;

public class UserEditDto {

    private String username;
    private String password;

    public UserEditDto() {
    }

    public UserEditDto(UserApplication user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserEditDto(String username, String password) {
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
