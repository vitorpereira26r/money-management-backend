package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.Category;
import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.TransactionDto;
import com.vitorpereira.money_management.entities.dtos.TransactionEditDto;
import com.vitorpereira.money_management.exceptions.DatabaseException;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<Transaction> getTransactionsByUsername(String username){
        List<Transaction> transactions = repository.findByUser_Username(username);
        return transactions;
    }

    public List<Transaction> getTransactionsByAccountId(Integer id){
        List<Transaction> transactions = repository.findByAccount_Id(id);
        return transactions;
    }

    public Transaction getTransactionById(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
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

    public void deleteTransactionById(Integer id){
        try{
            if(repository.existsById(id)){
                repository.deleteById(id);
            }
            else{
                throw new ResourceNotFoundException(id);
            }
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(TransactionEditDto dto, Transaction entity){
        entity.setType(dto.getType());
        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
    }
}
