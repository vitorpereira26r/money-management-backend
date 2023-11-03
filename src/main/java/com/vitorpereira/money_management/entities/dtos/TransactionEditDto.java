package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.enums.TransactionType;

public class TransactionEditDto {

    private TransactionType type;
    private Double amount;
    private String description;

    public TransactionEditDto() {
    }

    public TransactionEditDto(Transaction transaction){
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
    }

    public TransactionEditDto(TransactionType type, Double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
