package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.Account;

public interface AccountService {

	public List<Account> findAll ();
	
	public Account findByUsername(String username);
}
