package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.Account;
import com.kt.booking.wrapper.AgentAccount;

public interface AccountService {

	public List<Account> findAll ();
	
	public Account findByUserName(String userName);
	
	public Account save(AgentAccount account);
}
