package com.BankingTest.Bank;

import com.BankingTest.Bank.Exception.AccountNotFoundException;
import com.BankingTest.Bank.Respository.AccountRepository;
import com.BankingTest.Bank.Service.accountService;
import com.BankingTest.Bank.entity.account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TRanferMoneyTest {


    private AutoCloseable autoCloseable;

        @BeforeEach
        void SetUp(){
            autoCloseable = MockitoAnnotations.openMocks(this);
            accountService=new accountService(accountRepository);

        }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    //Assumption Step
    @Mock
    private  AccountRepository accountRepository;
    @InjectMocks
    private accountService accountService;
    @DisplayName("Happy Money")
    @Test
    public void TransferMoneyHappyFlow(){


        account sender=new account();
        sender.setId(1);
        sender.setSolde(new BigDecimal(10000));

        account recvier =new account();
        recvier.setId(2);
        recvier.setSolde(new BigDecimal(2900));


        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));

        given(accountRepository.findById(2L)).willReturn(Optional.of(recvier));
        //call step
        accountService.TransferMoney(sender.getId(),recvier.getId(),new BigDecimal(900));
        //verification step

        verify(accountRepository).save(sender);  // After money subtraction
        verify(accountRepository).save(recvier); // After money addition


        assert sender.getSolde().equals(new BigDecimal(9100));
        assert recvier.getSolde().equals(new BigDecimal(3800));



    }

    @DisplayName("Account not found test")
    @Test
    public void AccountRecevierNotFound(){
        account sender=new account();
        sender.setSolde(new BigDecimal(10000));
        sender.setId(1L);
        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.empty());
        //end of assumption step

        //call and verification step

        AccountNotFoundException exception=assertThrows(AccountNotFoundException.class, () -> {
            accountService.TransferMoney(sender.getId(), 2L, new BigDecimal(900));
        });

        assertEquals("account not found", exception.getMessage());

          verify(accountRepository,never()).save(any());



    }


    @Test
    public void InsuffisantFundsTest(){
        //Assumption Step
        //Define Data to use
        account sender=new account();
        account recevier=new account();

        sender.setId(1L);
        sender.setSolde(new BigDecimal(1000));
        recevier.setId(2L);
        recevier.setSolde(new BigDecimal(12000));

        //prepare mocking object return

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.of(recevier));

        //call and verify step

        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,
                ()->accountService.TransferMoney(1L,2L,new BigDecimal(300000))
                );

        assertEquals("Insufficient funds. Solde cannot be negative.",exception.getMessage());

    }



}
