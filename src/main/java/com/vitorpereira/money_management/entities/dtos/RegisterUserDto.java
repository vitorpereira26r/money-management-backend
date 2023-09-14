package com.vitorpereira.money_management.entities.dtos;

public class RegisterUserDto {

    private String username;
    private String password;

    public RegisterUserDto(){
    }

    public RegisterUserDto(String username, String password){
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
