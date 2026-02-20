package com.banking.accountService.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banking.accountService.model.AccountStatusEnum;
import com.banking.accountService.model.AccountTypeEnum;

public record AccountResponseRecord(Long accountId, String accountHolderName, AccountTypeEnum accountType, BigDecimal balance,  AccountStatusEnum status, LocalDateTime createdAt,
		 double minBalance, double overdraftLimit, double interestRate ) {


}
