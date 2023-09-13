package com.vitorpereira.money_management.repository;

import com.vitorpereira.money_management.entities.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Integer> {

    Optional<UserApplication> findByUsername(String username);
}
