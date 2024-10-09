package com.BankingTest.Bank;


import com.BankingTest.Bank.Respository.AccountRepository;
import com.BankingTest.Bank.entity.account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("Test creating new account ")
    public void TestSaveAccount(){
        account user= account.builder()
                .username("user1")
                .solde(new BigDecimal(10000))
                .build();

        account userTest=accountRepository.save(user);

        assertThat(entityManager.find(account.class,userTest.getId())).isEqualTo(user);
    }

    @Test
    public void TestUpdatingAccount(){
        account user= account.builder()
            .username("user1")
            .solde(new BigDecimal(10000))
            .build();

        entityManager.persist(user);

        String NewName="User2";

        user.setUsername(NewName);

        account userTest=accountRepository.save(user);

        assertThat(entityManager.find(account.class,userTest.getId()).getUsername()).isEqualTo(NewName);


    }

    @Test
    public void TestFindById(){
        account user= account.builder()
                .username("user1")
                .solde(new BigDecimal(10000))
                .build();

        entityManager.persist(user);

        Optional<account> TestUser=accountRepository.findById(user.getId());

        assertThat(TestUser).contains(user);

    }


    @Test

    public void TestDeleteBy(){

        account user= account.builder()
                .username("user1")
                .solde(new BigDecimal(10000))
                .build();
        entityManager.persist(user);

        accountRepository.deleteById(user.getId());
        assertThat(entityManager.find(account.class,user.getId())).isNull();
    }


}
