package com.vitorpereira.money_management.entities.dtos;

import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.enums.TransactionType;

public class TransactionDto {

    private TransactionType type;
    private Double amount;
    private String description;

    private Integer accountId;
    private Integer userId;
    private Integer categoryId;

    public TransactionDto() {
    }

    public TransactionDto(Transaction transaction){
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();

        if(transaction.getAccount() != null){
            this.accountId = transaction.getAccount().getId();
        }
        else{
            this.accountId = 0;
        }

        if(transaction.getUser() != null){
            this.userId = transaction.getUser().getId();
        }
        else{
            this.userId = 0;
        }

        if(transaction.getCategory() != null){
            this.categoryId = transaction.getCategory().getId();
        }
        else{
            this.categoryId = 0;
        }
    }

    public TransactionDto(
            TransactionType type,
            Double amount,
            String description,
            Integer accountId,
            Integer userId,
            Integer categoryId
    ) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
        this.userId = userId;
        this.categoryId = categoryId;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
