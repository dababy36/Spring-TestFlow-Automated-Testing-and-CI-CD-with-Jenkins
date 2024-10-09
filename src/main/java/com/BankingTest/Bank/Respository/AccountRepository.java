package com.BankingTest.Bank.Respository;


import com.BankingTest.Bank.entity.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<account,Long> {
}
