package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.Category;
import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.TransactionDto;
import com.vitorpereira.money_management.entities.dtos.TransactionEditDto;
import com.vitorpereira.money_management.entities.enums.TransactionType;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction createTransaction(TransactionDto dto, UserApplication user, Account account, Category category){

        Transaction transaction = new Transaction(
                null,
                Instant.now(),
                dto.getType(),
                dto.getAmount(),
                dto.getDescription(),
                account,
                user,
                category
        );

        return repository.save(transaction);
    }

    public List<Transaction> getTransactionsByUserId(Integer id){
        List<Transaction> transactions = repository.findByUser_Id(id);
        return transactions;
    }

    public List<Transaction> getTransactionsByAccountId(Integer id){
        List<Transaction> transactions = repository.findByAccount_Id(id);
        return transactions;
    }

    public Transaction editTransaction(TransactionEditDto dto, Integer id){
        try{
            Transaction entity = repository.getReferenceById(id);
            updateData(dto, entity);
            return repository.save(entity);
        }
        catch(EntityNotFoundException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(TransactionEditDto dto, Transaction entity){
        entity.setType(dto.getType());
        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
    }
}
