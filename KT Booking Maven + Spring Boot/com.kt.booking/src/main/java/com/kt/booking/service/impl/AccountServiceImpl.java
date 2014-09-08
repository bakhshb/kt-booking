package com.kt.booking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.booking.model.Account;
import com.kt.booking.model.Agent;
import com.kt.booking.repository.AccountRepository;
import com.kt.booking.repository.AgentRepository;
import com.kt.booking.repository.RoleRepository;
import com.kt.booking.service.AccountService;
import com.kt.booking.wrapper.AgentAccount;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	@Transactional
	public Account findByUserName(String username) {
		return accountRepository.findByUserName(username);
	}

	@Override
	@Transactional
	public Account save(AgentAccount agentAccountWrapper) {	
		Agent agent = new Agent();
		agent.setName(agentAccountWrapper.getName());
		agent.setEmail(agentAccountWrapper.getEmail());
		agent.setAddress(agentAccountWrapper.getAddress());
		agent.setContactNo(agentAccountWrapper.getContactNo());
		agent.setCreated(new Date());
		agent.setCreatedBy(agentAccountWrapper.getCreatedBy());
		Account account = new Account();
		account.setAgent(agent);
		account.setRole(roleRepository.findByRole(agentAccountWrapper.getRole()));
		account.setUserName(agentAccountWrapper.getUserName());
		account.setPassword(agentAccountWrapper.getPassword());
		account.setEnabled(true);
		return accountRepository.save(account);
	}

}
