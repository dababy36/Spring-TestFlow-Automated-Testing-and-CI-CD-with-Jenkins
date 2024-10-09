package com.BankingTest.Bank.Service;

import com.BankingTest.Bank.entity.account;

import java.math.BigDecimal;

public interface IaccountService  {

    public account GetAccountById(Long Id) ;

    public  String TransferMoney(Long Idsender, Long Idreciever, BigDecimal amount);

    public account addAccount(account account);

    public account UpdateAccountAddsolde(Long Id,BigDecimal amount);
    public account UpdateAccountSubtractsolde(Long Id,BigDecimal amount);




}
