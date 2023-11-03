package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.exceptions.DatabaseException;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.AccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(String name, UserApplication user){

        Account account = new Account(null, name, 0.0, user);

        return repository.save(account);
    }

    public List<Account> getAccountByUserId(Integer id){

        List<Account> accounts = repository.findByUser_Id(id);
        return accounts;
    }

    public Account editAccount(String name, Integer id){

        Account acc = repository.getReferenceById(id);

        acc.setName(name);

        return repository.save(acc);
    }

    public Account updateBalance(Double value, Integer id){

        Account acc = repository.getReferenceById(id);

        Double aux = acc.getBalance() + value;
        acc.setBalance(aux);

        return repository.save(acc);
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
