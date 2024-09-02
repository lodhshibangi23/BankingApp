package com.example.Banking_app.mapper;

import com.example.Banking_app.dto.AccountDto;
import com.example.Banking_app.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto) {
		
		Account account = new Account(
				accountDto.getId(),
				accountDto.getHolderName(),
				accountDto.getBalance()
				);
		
		return account;
	}
	
public static AccountDto mapToAccountDto(Account account) {
		
		AccountDto accountDto = new AccountDto(
				account.getId(),
				account.getHolderName(),
				account.getBalance()
				);
		
		return accountDto;
	}

}