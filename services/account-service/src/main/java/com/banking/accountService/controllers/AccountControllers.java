package com.banking.accountService.controllers;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.accountService.dto.AccountRequestRecord;
import com.banking.accountService.dto.AccountResponseRecord;
import com.banking.accountService.services.AccountServices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/accounts")
public class AccountControllers {

    private final AccountServices accountService;

    public AccountControllers(AccountServices accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponseRecord createAccount(@Valid @RequestBody AccountRequestRecord dto){
        return accountService.createAccount(dto);
    }

    @GetMapping("/{id}") @Validated
    public AccountResponseRecord getAccount(@Positive @PathVariable Long id){
        return accountService.getAccount(id);
    }

    @PostMapping("/deposit/{id}")@Validated
    public AccountResponseRecord deposit(@Positive @PathVariable Long id,@Positive @RequestParam BigDecimal amount){
        return accountService.deposit(id, amount);
    }

    @PostMapping("/withdraw/{id}")@Validated
    public AccountResponseRecord withdraw(@Positive @PathVariable Long id,@Positive @RequestParam BigDecimal amount){
        return accountService.withdraw(id, amount);
    }
    
    @PatchMapping("/close/{id}")@Validated
    public void closeAccountById(@Positive @PathVariable Long id) { 
    	 accountService.closeAccountById(id);
    }
    
    
}
