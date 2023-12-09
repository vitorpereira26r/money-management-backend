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
            Account acc = accountService.getAccountById(id);
            Double amount = acc.getBalance() * (-1);

            userService.updateBalance(amount, acc.getUser().getId());

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
        Transaction transactionBeforeEdit = transactionService.getTransactionById(id);
        TransactionType typeBeforeEdit = transactionBeforeEdit.getType();
        Double amountBeforeEdit = transactionBeforeEdit.getAmount();
        Transaction transaction = transactionService.editTransaction(dto, id);

        System.out.println("Type before edit: " + typeBeforeEdit);
        System.out.println("Amount before edit: " + amountBeforeEdit);

        System.out.println("Type after edit: " + transaction.getType());
        System.out.println("Amount after edit: " + transaction.getAmount());

        Double amount = 0.0;
        if(typeBeforeEdit == TransactionType.EXPENSE){
            if(transaction.getType() == TransactionType.EXPENSE){
                System.out.println(1);
                amount = amountBeforeEdit - transaction.getAmount();
            }
            else if(transaction.getType() == TransactionType.INCOME){
                System.out.println(2);
                amount = amountBeforeEdit + transaction.getAmount();
            }
        }
        if(typeBeforeEdit == TransactionType.INCOME){
            if(transaction.getType() == TransactionType.EXPENSE){
                System.out.println(3);
                amount = (amountBeforeEdit + transaction.getAmount()) * (-1);
            }
            else if(transaction.getType() == TransactionType.INCOME){
                System.out.println(4);
                amount = (amountBeforeEdit - transaction.getAmount()) * (-1);
            }
        }
        System.out.println("Amount: " + amount);

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
