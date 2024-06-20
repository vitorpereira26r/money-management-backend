package com.vitorpereira.money_management.repository;

import com.vitorpereira.money_management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByName(String name);
    List<Account> findByUser_Id(Integer userId);
    List<Account> findByUser_Username(String username);
}
