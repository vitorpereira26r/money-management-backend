package com.vitorpereira.money_management.entities.enums;

public enum TransactionType {

    INCOME(1),
    EXPENSE(2);

    private int code;

    private TransactionType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static TransactionType valueOf(int code){
        for(TransactionType t : TransactionType.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid Transaction Type");
    }
}
