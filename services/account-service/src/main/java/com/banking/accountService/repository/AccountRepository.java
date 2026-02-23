package com.banking.accountService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.banking.accountService.model.AccountModel;

import jakarta.persistence.LockModeType;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long>{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<AccountModel> findById(Long id);
}
