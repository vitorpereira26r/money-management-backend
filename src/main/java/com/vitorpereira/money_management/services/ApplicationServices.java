package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Account;
import com.vitorpereira.money_management.entities.Category;
import com.vitorpereira.money_management.entities.Transaction;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.entities.dtos.AccountDto;
import com.vitorpereira.money_management.entities.dtos.TransactionDto;
import com.vitorpereira.money_management.entities.dtos.TransactionEditDto;
import com.vitorpereira.money_management.entities.enums.TransactionType;
import com.vitorpereira.money_management.exceptions.DatabaseException;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServices {

    private AccountService accountService;

    private UserService userService;

    private TransactionService transactionService;

    private CategoryService categoryService;

    public ApplicationServices(
            AccountService accountService,
            UserService userService,
            TransactionService transactionService,
            CategoryService categoryService
    ) {
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    public List<Account> getAccountByUserId(Integer id){
        List<Account> accounts = accountService.getAccountByUserId(id);

        return accounts;
    }

    public Account createAccount(AccountDto dto){

        UserApplication user = userService.getUserById(dto.getUserId());

        Account newAccount = accountService.createAccount(dto.getName(), user);

        return newAccount;
    }

    public Account editAccountName(String name, Integer id){
        Account account = accountService.editAccountName(name, id);

        return account;
    }

    public void deleteAccount(Integer id){
        try{
            accountService.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Transaction> getTransactionByUserId(Integer id){
        List<Transaction> transactions = transactionService.getTransactionsByUserId(id);
        return transactions;
    }

    public List<Transaction> getTransactionsByAccountId(Integer id){
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(id);
        return transactions;
    }

    public Transaction createTransaction(TransactionDto transactionDto){

        Double amount = 0.0;
        if(transactionDto.getType() == TransactionType.EXPENSE){
            amount = transactionDto.getAmount() * (-1);
        }
        else{
            amount = transactionDto.getAmount();
        }

        Category category = categoryService.getCategoryById(transactionDto.getCategoryId());

        Account account = accountService.updateBalance(amount, transactionDto.getAccountId());
        UserApplication user = userService.updateBalance(amount, transactionDto.getUserId());

        Transaction transaction = transactionService.createTransaction(transactionDto, user, account, category);

        return transaction;
    }

    public Transaction editTransaction(TransactionEditDto dto, Integer id){
        Transaction transaction = transactionService.editTransaction(dto, id);

        Double amount = 0.0;
        if(transaction.getType() == TransactionType.EXPENSE){
            amount = transaction.getAmount() * (-1);
        }
        else{
            amount = transaction.getAmount();
        }

        UserApplication user = transaction.getUser();
        Account account = transaction.getAccount();

        accountService.updateBalance(amount, account.getId());
        userService.updateBalance(amount, user.getId());

        return transaction;
    }

    public void deleteTransaction(Integer id){
        Transaction transaction = transactionService.getTransactionById(id);

        Double amount = 0.0;
        if(transaction.getType() == TransactionType.EXPENSE){
            amount = transaction.getAmount();
        }
        else{
            amount = transaction.getAmount() * (-1);
        }

        UserApplication user = transaction.getUser();
        Account account = transaction.getAccount();

        accountService.updateBalance(amount, account.getId());
        userService.updateBalance(amount, user.getId());

        transactionService.deleteTransactionById(id);
    }

    public List<Category> getCategories(){
        return categoryService.getCategories();
    }
}
