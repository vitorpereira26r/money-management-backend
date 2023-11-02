package com.vitorpereira.money_management.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Instant date;
    private Integer type;
    private Double amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApplication user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction(){
    }

    public Transaction(
            Integer id,
            Instant date,
            Integer type,
            Double amount,
            String description,
            Account account,
            UserApplication user,
            Category category
    ) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.user = user;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UserApplication getUser() {
        return user;
    }

    public void setUser(UserApplication user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", account=" + account +
                ", user=" + user +
                '}';
    }
}
