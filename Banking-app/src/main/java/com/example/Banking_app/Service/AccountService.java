package com.example.Banking_app.Service;

import java.util.List;

import com.example.Banking_app.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto account);
	
	AccountDto getAccountById(Long Id);
	
	AccountDto deposit(Long Id,double amount);
	
	AccountDto withdraw(Long Id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long Id);
	
}
