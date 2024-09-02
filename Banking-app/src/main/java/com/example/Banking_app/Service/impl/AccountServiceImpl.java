package com.example.Banking_app.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Banking_app.Service.AccountService;
import com.example.Banking_app.dto.AccountDto;
import com.example.Banking_app.entity.Account;
import com.example.Banking_app.mapper.AccountMapper;
import com.example.Banking_app.repository.AccountRepo;


@Service
public class AccountServiceImpl implements AccountService{
	
	private static final AccountDto accountDto = null;
	private AccountRepo accountrepo;
	
	@Autowired
	public AccountServiceImpl(AccountRepo accountrepo) {
		super();
		this.accountrepo = accountrepo;
	}



	@Override
	//accountDto -> accnt jpa -> datatbase ->back to dto== conversion logic
	public AccountDto createAccount(AccountDto accountDto) {
	    // Convert AccountDto to Account entity
		Account account = AccountMapper.mapToAccount(accountDto);
		
	    // Save the Account entity to the database
		Account savedAccount = accountrepo.save(account);
		
	    // Convert the saved Account entity back to AccountDto
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}



	@Override
	public AccountDto getAccountById(Long Id) {
		
		Account account = accountrepo
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long Id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountrepo
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		double total = account.getBalance()+amount;
		account.setBalance(total);
		
		Account savedAccount = accountrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	
	}



	@Override
	public AccountDto withdraw(Long Id, double amount) {
		
		Account account = accountrepo
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
				}
		
		double total = account.getBalance()-amount;
		account.setBalance(total);
		
		Account savedAccount = accountrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
		
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts =  accountrepo.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
				
		
	}



	@Override
	public void deleteAccount(Long Id) {
		// TODO Auto-generated method stub

		Account account = accountrepo
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountrepo.deleteById(Id);
		
	}
	
	

	

}
