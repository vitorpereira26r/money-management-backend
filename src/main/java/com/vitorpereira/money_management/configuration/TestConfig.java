package com.vitorpereira.money_management.configuration;

import com.vitorpereira.money_management.entities.*;
import com.vitorpereira.money_management.entities.enums.TransactionType;
import com.vitorpereira.money_management.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private AccountRepository accountRepository;
    private CategoryRepository categoryRepository;
    private RoleRepository roleRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public TestConfig(
            AccountRepository accountRepository,
            CategoryRepository categoryRepository,
            RoleRepository roleRepository,
            TransactionRepository transactionRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByAuthority("admin").isPresent()) return;

        Role adminRole = roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        UserApplication admin = new UserApplication(null, "admin", passwordEncoder.encode("password"), roles);
        userRepository.save(admin);

        Account account = new Account(null, "Itaú", 0.0, admin);
        accountRepository.save(account);

        Category category1 = new Category(null, "Buy");
        Category category2 = new Category(null, "Earn");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Transaction transaction = new Transaction(null, Instant.now(), TransactionType.EXPENSE, 0.0, "buy", account, admin, category1);
        transactionRepository.save(transaction);
    }
}
