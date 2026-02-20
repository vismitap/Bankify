package com.banking.accountService.dto;

import java.math.BigDecimal;

import com.banking.accountService.model.AccountTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequestRecord(@NotBlank @NotBlank String accountHolderName, @NotNull AccountTypeEnum accountType, @NotNull BigDecimal initialDeposit) {
	
}
