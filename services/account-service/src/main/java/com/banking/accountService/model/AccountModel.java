package com.banking.accountService.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	private boolean kycDone;
	
	private String accountHolderName;
	
    @Column(precision = 10, scale = 2)
	private BigDecimal balance = new BigDecimal("100.00");
	private LocalDateTime createdAt;
	
	@Enumerated(EnumType.STRING)
	private AccountTypeEnum accountType;
	
    @Enumerated(EnumType.STRING)
	private AccountStatusEnum accountstatus;

	//optional fields
	private double minBalance;
	private double overdraftLimit;
	private double interestRate;
}
