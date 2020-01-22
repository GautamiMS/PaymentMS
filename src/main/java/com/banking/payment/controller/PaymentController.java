package com.banking.payment.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.payment.dto.AccountDTO;
import com.banking.payment.dto.TransactionDTO;
import com.banking.payment.exceptions.InsufficientFundException;
import com.banking.payment.service.AccountService;

@RestController
public class PaymentController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionFeign transactionFeign;

	@PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountDTO updateAccount(@Valid @RequestBody AccountDTO accountDTO) {

		logger.info("Update account request, inside Controller. RequestBody : ", accountDTO);

		AccountDTO accountDTO2 = accountService.getAccount(accountDTO.getAccountNumber());

		if (accountDTO.getBalance() > accountDTO2.getBalance()) {
			throw new InsufficientFundException("You have insufficient fund. Please try again!");
		} else {

			double balance = accountDTO2.getBalance() - accountDTO.getBalance();

			accountDTO2.setBalance(balance);
			accountService.updateAccount(accountDTO2);

			TransactionDTO transactionDTO = new TransactionDTO();
			transactionDTO.setAccountNumber(accountDTO2.getAccountNumber());
			transactionDTO.setAmount(accountDTO.getBalance());
			transactionDTO.setComments("Payment for DishTV");
			transactionDTO.setOperation("Debit");
			Date newDate = new Date();
			transactionDTO.setTimestamp(newDate);

			transactionFeign.saveTransaction(transactionDTO);
		}

		return accountDTO2;
	}
}