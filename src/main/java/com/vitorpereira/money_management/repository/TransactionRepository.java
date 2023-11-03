package com.vitorpereira.money_management.repository;

import com.vitorpereira.money_management.entities.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUser_Id(Integer id);
    List<Transaction> findByAccount_Id(Integer id);
    List<Transaction> findByCategory_Id(Integer id);
}
