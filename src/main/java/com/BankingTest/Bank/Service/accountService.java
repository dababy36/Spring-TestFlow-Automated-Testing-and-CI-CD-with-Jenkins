package com.BankingTest.Bank.Service;


import com.BankingTest.Bank.Exception.AccountNotFoundException;
import com.BankingTest.Bank.Respository.AccountRepository;
import com.BankingTest.Bank.entity.account;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class accountService implements IaccountService{

    private final AccountRepository accountRepository;
    @Override
    public account GetAccountById(Long Id) {
        account account =accountRepository.findById(Id).orElseThrow(
                ()->new AccountNotFoundException("account not found")

        );
        return account;
    }

    @Override
    @Transactional
    public String TransferMoney(Long Idsender, Long Idreciever, BigDecimal amountTransfer) {

        if (amountTransfer == null || amountTransfer.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        account sender = GetAccountById(Idsender);account recevier=GetAccountById(Idreciever);
            if(amountTransfer.compareTo(sender.getSolde())>0){
                throw new IllegalArgumentException("Insufficient funds. Solde cannot be negative.");
            }else{
                UpdateAccountSubtractsolde(Idsender,amountTransfer);
                UpdateAccountAddsolde(Idreciever,amountTransfer);
                return "Money transfer successful";
            }



    }

    @Override
    public account addAccount(account account) {
        if(account!=null){
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public account UpdateAccountAddsolde(Long Id,BigDecimal amountToadd) {

        account account=GetAccountById(Id);

        account.setSolde(account.getSolde().add(amountToadd));
        return accountRepository.save(account);
    }

    @Override
    public account UpdateAccountSubtractsolde(Long Id, BigDecimal amountToSubtract) {
        account account =GetAccountById(Id);
        BigDecimal updatedSolde = account.getSolde().subtract(amountToSubtract);

        if (updatedSolde.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds. Solde cannot be negative.");
        }

        account.setSolde(updatedSolde);

        return accountRepository.save(account);
    }
}
