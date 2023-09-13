package com.vitorpereira.money_management;

import com.vitorpereira.money_management.entities.Role;
import com.vitorpereira.money_management.entities.UserApplication;
import com.vitorpereira.money_management.repository.RoleRepository;
import com.vitorpereira.money_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MoneyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder){
		return  args -> {
			if(roleRepository.findByAuthority("admin").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserApplication admin = new UserApplication(0, "admin", encoder.encode("password"), roles);

			userRepository.save(admin);
		};
	}
}
