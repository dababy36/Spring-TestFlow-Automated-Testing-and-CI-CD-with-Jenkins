package com.BankingTest.Bank;

import com.BankingTest.Bank.Respository.AccountRepository;
import com.BankingTest.Bank.entity.account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankApplication.class, args);

	}
	@Bean
	public CommandLineRunner init(AccountRepository accountRepository) {
		return args -> {
			// Create first user account
			account user1Account = account.builder()
					.email("user1@gmail.com")
					.username("Ahmed")
					.solde(new BigDecimal("100000.0"))
					.build();

			// Create second user account
			account user2Account = account.builder()
					.email("user2@gmail.com")
					.username("Sarah")
					.solde(new BigDecimal("50000.0"))
					.build();

			// Save both accounts to the database
			accountRepository.save(user1Account);
			accountRepository.save(user2Account);

			// Log account details to the console
			System.out.println("User 1 Account created: " + user1Account);
			System.out.println("User 2 Account created: " + user2Account);
		};
	}

}
