package com.example.financetracker;

import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.models.Role;
import com.example.financetracker.repository.RoleRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FinanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceTrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
		 	roleRepository.save(new Role("USER"));


		 	Set<Role> roles = new HashSet<>();
		 	roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1L, "admin", passwordEncoder.encode("password"), "email.exa.com",  roles );

			userRepository.save(admin);
		};
	}

}
