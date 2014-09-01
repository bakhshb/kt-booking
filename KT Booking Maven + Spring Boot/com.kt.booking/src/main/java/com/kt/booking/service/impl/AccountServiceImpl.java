package com.kt.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.booking.model.Account;
import com.kt.booking.repository.AccountRepository;
import com.kt.booking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	@Transactional
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
