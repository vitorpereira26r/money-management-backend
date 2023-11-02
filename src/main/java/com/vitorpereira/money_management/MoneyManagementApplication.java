package com.vitorpereira.money_management;

import com.vitorpereira.money_management.entities.*;
import com.vitorpereira.money_management.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MoneyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(
            AccountRepository accountRepository,
            CategoryRepository categoryRepository,
            RoleRepository roleRepository,
            TransactionRepository transactionRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
		return  args -> {
			if(roleRepository.findByAuthority("admin").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserApplication admin = new UserApplication(null, "admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);

			Account account = new Account(null, "Itaú", 0.0, admin);
			accountRepository.save(account);

			Category category = new Category(null, "Buy");
			categoryRepository.save(category);

			Transaction transaction = new Transaction(null, Instant.now(), 0, 0.0, "buy", account, admin, category);
			transactionRepository.save(transaction);
		};
	}
}
