package com.banking.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.payment.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
