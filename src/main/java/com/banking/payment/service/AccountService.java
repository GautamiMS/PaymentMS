package com.banking.payment.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.payment.dto.AccountDTO;
import com.banking.payment.entity.Account;
import com.banking.payment.repository.AccountRepository;

@Service
public class AccountService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountRepository accountRepository;

	public AccountDTO getAccount(Integer accountNumber) {

		logger.info("Get account request, inside Service. AccNumber : ", accountNumber);

		Account account = accountRepository.findById(accountNumber).get();

		AccountDTO accountDTO = AccountDTO.valueOf(account);

		return accountDTO;

	}

	public void updateAccount(AccountDTO accountDTO) {

		logger.info("Update account request, inside Service. RequestBody : ", accountDTO);

		Account account = accountDTO.createEntity();

		accountRepository.save(account);
	}
}