package com.banking.payment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.banking.payment.entity.Account;

public class AccountDTO {
	@NotNull(message = "Account number can't be null")
	@Min(10)
	Integer accountNumber;

	@NotNull(message = "Name can't be null")
	String name;

	@NotNull(message = "Balance can't be null")
	Double balance;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public static AccountDTO valueOf(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNumber(account.getAccountNumber());
		accountDTO.setBalance(account.getBalance());
		accountDTO.setName(account.getName());

		return accountDTO;
	}

	public Account createEntity() {
		Account account = new Account();
		account.setAccountNumber(this.getAccountNumber());
		account.setBalance(this.getBalance());
		account.setName(this.getName());

		return account;
	}

}