package com.banking.accountService.services;

import java.math.BigDecimal;

import com.banking.accountService.dto.AccountRequestRecord;
import com.banking.accountService.dto.AccountResponseRecord;

public interface IAccountServices {
	AccountResponseRecord createAccount(AccountRequestRecord dto);
    AccountResponseRecord getAccount(Long accountId);
    AccountResponseRecord deposit(Long accountId, BigDecimal amount);
    AccountResponseRecord withdraw(Long accountId, BigDecimal amount);
    void closeAccountById(Long accountId);
}
