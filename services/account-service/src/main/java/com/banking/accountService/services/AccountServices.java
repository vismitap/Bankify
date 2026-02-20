package com.banking.accountService.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.banking.accountService.dto.AccountRequestRecord;
import com.banking.accountService.dto.AccountResponseRecord;
import com.banking.accountService.exception.AccountNotFoundException;
import com.banking.accountService.exception.ErrorCodeEnum;
import com.banking.accountService.exception.InsufficientBalanceException;
import com.banking.accountService.exception.InvalidOperationException;
import com.banking.accountService.model.AccountModel;
import com.banking.accountService.model.AccountStatusEnum;
import com.banking.accountService.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServices implements IAccountServices{

	private final AccountRepository repo;
	public AccountServices(AccountRepository repo){
		this.repo = repo;
	}
	
	private AccountResponseRecord mapToResponse(AccountModel savedAccountResponse){
	    return new AccountResponseRecord(
	    		savedAccountResponse.getAccountId(),
	    		savedAccountResponse.getAccountHolderName(),
	    		savedAccountResponse.getAccountType(),
	    		savedAccountResponse.getBalance(),
	    		savedAccountResponse.getAccountstatus(),
	    		savedAccountResponse.getCreatedAt(),
	    		savedAccountResponse.getMinBalance(),
	    		savedAccountResponse.getOverdraftLimit(),
	    		savedAccountResponse.getInterestRate()
	    );
	}

	
	@Override
	public AccountResponseRecord createAccount(AccountRequestRecord dto) {
		// TODO Auto-generated method stub
		AccountModel account = new AccountModel();
		if(dto.accountHolderName()!=null && !dto.accountHolderName().isEmpty())  account.setAccountHolderName(dto.accountHolderName().trim());
		if(dto.accountType()!=null)  account.setAccountType(dto.accountType());
		account.setAccountstatus(AccountStatusEnum.ACTIVE);
		account.setCreatedAt(LocalDateTime.now());
		account.setKycDone(false);
		account.setMinBalance(0);
		account.setOverdraftLimit(1000);		
		if(dto.initialDeposit()!=null) account.setBalance(dto.initialDeposit());
		
		return mapToResponse(repo.save(account));
	}

	@Override
	public AccountResponseRecord getAccount(Long accountId) {

		return mapToResponse(repo.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ErrorCodeEnum.ACCOUNT_NOT_FOUND)));
	}

	@Override
	@Transactional
	public AccountResponseRecord deposit(Long accountId, BigDecimal amount) {
		
	    AccountModel account = repo.findById(accountId)
	            .orElseThrow(() -> new AccountNotFoundException(ErrorCodeEnum.ACCOUNT_NOT_FOUND));

	   if(account.getBalance()!=null) account.setBalance(account.getBalance().add(amount));

	    return mapToResponse(repo.save(account));

	}

	@Override
	@Transactional
	public AccountResponseRecord withdraw(Long accountId, BigDecimal amount) {
		
		AccountModel account = repo.findById(accountId)
	            .orElseThrow(() -> new AccountNotFoundException(ErrorCodeEnum.ACCOUNT_NOT_FOUND));
		if(account.getBalance()!=null) account.setBalance(account.getBalance().subtract(amount));
		
		return mapToResponse(account);
	}

	@Override
	@Transactional
	public void closeAccountById(Long accountId) {
				
		AccountModel account = repo.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ErrorCodeEnum.ACCOUNT_NOT_FOUND));
		
		if(account.getAccountstatus()!=null && account.getAccountstatus().equals(AccountStatusEnum.CLOSED)) {
			throw new InvalidOperationException(ErrorCodeEnum.ACCOUNT_ALREADY_CLOSED);
		}
		
		if(account.getBalance().compareTo(BigDecimal.ZERO)==0) {
			throw new InsufficientBalanceException(ErrorCodeEnum.INSUFFICIENT_BALANCE);
		}
		
		account.setAccountstatus(AccountStatusEnum.CLOSED);
		AccountModel savedAccount = repo.save(account);
	}

}
