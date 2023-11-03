package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.exceptions.DatabaseException;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getAccountById(Integer id){
        Optional<Account> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Account createAccount(String name, UserApplication user){

        Account account = new Account(null, name, 0.0, user);

        return repository.save(account);
    }

    public List<Account> getAccountByUserId(Integer id){

        List<Account> accounts = repository.findByUser_Id(id);
        return accounts;
    }

    public Account editAccountName(String name, Integer id){
        try{
            Account acc = repository.getReferenceById(id);

            acc.setName(name);

            return repository.save(acc);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Account updateBalance(Double value, Integer id){
        try{
            Account acc = repository.getReferenceById(id);

            Double aux = acc.getBalance() + value;
            acc.setBalance(aux);

            return repository.save(acc);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void deleteById(Integer id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
